package br.com.wellcar.controller.advice;

import br.com.wellcar.exception.CarNullException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CarControllerAdvice {
    @ExceptionHandler(CarNullException.class)
    public ResponseEntity<Object> catchFindCarNullError(Long id) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", "ERRO: Carro não encontrado com este ID:");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);

    }
}
