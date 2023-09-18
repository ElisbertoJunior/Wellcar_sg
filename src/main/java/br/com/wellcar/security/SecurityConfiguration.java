package br.com.wellcar.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, "swagger-ui/**", "v3/api-docs/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "swagger-ui/**", "v3/api-docs/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "swagger-ui/**", "v3/api-docs/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "swagger-ui/**", "v3/api-docs/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "auth/register").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "report/create").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "report/period").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "report/is-open").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "report/all-reports").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "report/delete/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "report/{id}").hasRole("ADMIN")
                        .anyRequest()
                        .authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
