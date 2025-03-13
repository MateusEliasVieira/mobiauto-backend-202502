package com.mobiauto.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.OffsetDateTime;

@ControllerAdvice
public class ManipuladorDeDominioException {
    @ExceptionHandler(RegrasDeNegocioException.class)
    public ResponseEntity<Problema> manipuladorDeDominioException(RegrasDeNegocioException ex) {

        var status = HttpStatus.NOT_FOUND;

        var problema = new Problema();
        problema.setStatus(status.value());
        problema.setTitle(ex.getMessage());
        problema.setDate(OffsetDateTime.now());

        return ResponseEntity.badRequest().body(problema);
    }
}