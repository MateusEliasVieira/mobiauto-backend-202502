package com.mobiauto.api.controller.login;

import com.mobiauto.api.dto.login.LoginInputDTO;
import com.mobiauto.domain.service.login.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService servico;

    @PostMapping("/logar")
    public ResponseEntity<?> login(@RequestBody @Valid LoginInputDTO loginEntradaDTO, HttpServletRequest request) {
        return servico.processarLogin(loginEntradaDTO);
    }

}