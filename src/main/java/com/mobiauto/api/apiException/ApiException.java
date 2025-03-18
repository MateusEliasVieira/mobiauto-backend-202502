package com.mobiauto.api.apiException;

import com.mobiauto.utils.Resposta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ApiException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Problema> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<Campo> lista = new ArrayList<Campo>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {

            Campo campo = new Campo();
            campo.setNomeCampo(error.getField());
            campo.setMensagem(error.getDefaultMessage());

           lista.add(campo);

        });

        var status = HttpStatus.BAD_REQUEST;

        var problema = new Problema();
        problema.setTitulo(Resposta.ERRO_PADRAO);
        problema.setData(OffsetDateTime.now());
        problema.setStatus(status.value());
        problema.setLista(lista);

        return ResponseEntity.badRequest().body(problema);
    }

}
