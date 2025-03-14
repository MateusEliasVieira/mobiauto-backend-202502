package com.mobiauto.utils;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.context.SecurityContextHolder;

public class UsuarioLogado {

    public static String getCnpjTokenUsuarioLogado() {
        // Obtém o token do contexto de segurança e extrai o CNPJ da revenda
        DecodedJWT decodedJWT = (DecodedJWT) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return decodedJWT.getClaim("revenda").asString(); // Recupera o CNPJ da revenda
    }

    public static String getPerfilTokenUsuarioLogado() {
        // Obtém o token do contexto de segurança e extrai o perfil do usuário logado
        DecodedJWT decodedJWT = (DecodedJWT) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return decodedJWT.getClaim("perfil").asString(); // Recupera o Perfil do usuário logado no sistema
    }
}