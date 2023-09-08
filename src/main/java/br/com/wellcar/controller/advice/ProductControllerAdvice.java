package br.com.wellcar.controller.advice;

import br.com.wellcar.exception.FindClientNullException;
import br.com.wellcar.exception.ProductNullException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ProductControllerAdvice {

    @ExceptionHandler(ProductNullException.class)
    public ResponseEntity<Object> catchFindNullError() {
        Map<String, Object> body = new HashMap<>();
        body.put("message", "ERRO: Produto não encontrado com este ID");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);

    }
}
