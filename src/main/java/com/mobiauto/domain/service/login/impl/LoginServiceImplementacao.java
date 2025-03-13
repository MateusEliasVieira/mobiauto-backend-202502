package com.mobiauto.domain.service.login.impl;

import com.mobiauto.api.controller.utils.Mensagem;
import com.mobiauto.api.dto.login.LoginInputDTO;
import com.mobiauto.api.dto.login.LoginOutputDTO;
import com.mobiauto.api.mapper.login.LoginMapeador;
import com.mobiauto.domain.model.Usuario;
import com.mobiauto.domain.service.login.LoginService;
import com.mobiauto.domain.service.usuario.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImplementacao implements LoginService {

    @Autowired
    private UsuarioService service;

    @Autowired
    public AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<?> processarLogin(LoginInputDTO loginEntradaDTO) {
        try {
            var nomeDeUsuarioESenha = new UsernamePasswordAuthenticationToken(loginEntradaDTO.getEmail(), loginEntradaDTO.getSenha());
            var autenticacao = authenticationManager.authenticate(nomeDeUsuarioESenha);
            if (autenticacao.isAuthenticated()) {
                Usuario usuarioLogado = service.login((Usuario) autenticacao.getPrincipal());
                return new ResponseEntity<LoginOutputDTO>(LoginMapeador.mapperUsuarioParaLoginOutputDTO(usuarioLogado), HttpStatus.ACCEPTED);
            }
        } catch (AuthenticationException e) {
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<Mensagem>(new Mensagem("Login inválido!"), HttpStatus.NOT_ACCEPTABLE);
    }

}
