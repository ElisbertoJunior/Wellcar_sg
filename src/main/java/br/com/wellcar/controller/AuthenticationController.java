package br.com.wellcar.controller;

import br.com.wellcar.DTO.LoginResponseDTO;
import br.com.wellcar.entity.User;
import br.com.wellcar.repository.UserRepository;
import br.com.wellcar.security.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Wellcar Authentication", description = "Endpoints for application access control")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    @Operation(summary = "Login the user to the application")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid User user) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok().body(new LoginResponseDTO(token));

    }

    @PostMapping("/register")
    @Operation(summary = "Registers a new user in the application")
    public ResponseEntity register(@RequestBody @Valid User user) {
        if(repository.findByLogin(user.getLogin()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        User newUser = new User(user.getLogin(), encryptedPassword, user.getRole());

        repository.save(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



}
