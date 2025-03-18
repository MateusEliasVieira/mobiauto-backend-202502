package com.mobiauto.utils;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.mobiauto.security.JwtToken;
import org.springframework.stereotype.Component;

@Component
public class UsuarioLogado {

    public String getCnpjTokenUsuarioLogado(String token) {
        // Obtém o token do contexto de segurança e extrai o CNPJ da revenda
        DecodedJWT decodedJWT = JwtToken.decodificarTokenJWT(token);
        return decodedJWT.getClaim("revenda").asString(); // Recupera o CNPJ da revenda
    }

    public String getPerfilTokenUsuarioLogado(String token) {
        // Obtém o token do contexto de segurança e extrai o perfil do usuário logado
        DecodedJWT decodedJWT = JwtToken.decodificarTokenJWT(token);
        return decodedJWT.getClaim("perfil").asString(); // Recupera o Perfil do usuário logado no sistema
    }

    public String getIDTokenUsuarioLogado(String token) {
        // Obtém o token do contexto de segurança e extrai o ID do usuário logado
        DecodedJWT decodedJWT = JwtToken.decodificarTokenJWT(token);
        System.out.println("idUsuario "+decodedJWT.getClaim("idUsuario").asString());
        return decodedJWT.getClaim("idUsuario").asString(); // Recupera o Perfil do usuário logado no sistema
    }

}