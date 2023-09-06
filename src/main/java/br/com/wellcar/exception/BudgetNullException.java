package br.com.wellcar.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

import java.io.Serial;

@ControllerAdvice
public class BudgetNullException extends NullPointerException {
    @Serial
    private static final long serialVersion = 1L;
}
