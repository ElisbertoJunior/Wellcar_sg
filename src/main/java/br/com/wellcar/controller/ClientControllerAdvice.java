package br.com.wellcar.controller;

import br.com.wellcar.exception.ClientNullException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ClientControllerAdvice {

    @ExceptionHandler(ClientNullException.class)
    public ResponseEntity<Object> catchNullError() {
        Map<String, Object> body = new HashMap<>();
        body.put("message", "ERRO: Verique os campos do clinte um ou mais estao vazios");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);

    }
}
