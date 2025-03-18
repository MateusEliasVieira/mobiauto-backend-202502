package com.mobiauto.security;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mobiauto.domain.model.Usuario;


public class JwtToken {

    private static final String EMISSOR = "Sistema@Mobiauto";
    private static final String TOKEN_KEY = "01234567890123456789012345678901"; // Chave deve ter 256 bits, nesse caso 32 caracteres, para a criptografia
    private static final long MINUTOS = 60;

    public static String gerarTokenJWT(Usuario usuario)
    {
        try{
            String token = JWT.create()
                    .withSubject(usuario.getUsername()) // Dono do token (Nome do usuário)
                    .withIssuer(EMISSOR)
                    .withExpiresAt(LocalDateTime.now().plusMinutes(MINUTOS).toInstant(ZoneOffset.of("-03:00")))
                    .withClaim("idUsuario", String.valueOf(usuario.getIdUsuario())) // ID do usuário
                    .withClaim("revenda", usuario.getRevenda().getCnpj()) // O token deve conter em qual revenda pertence a pessoa que está usando o token e realizando operações
                    .withClaim("perfil", String.valueOf(usuario.getPerfil().toString())) // Perfil do usuário
                    .sign(Algorithm.HMAC256(TOKEN_KEY.getBytes())); // Assinatura

            return token;

        }catch(JWTCreationException jwtCreationException){
            throw new RuntimeException(jwtCreationException.getMessage());
        }

    }

    public static DecodedJWT decodificarTokenJWT(String token){
        try{
            // A segurança do token, está na chave, apenas eu tenho
            DecodedJWT decode = JWT.require(Algorithm.HMAC256(TOKEN_KEY))
                    .withIssuer(EMISSOR)
                    .build()
                    .verify(token);// verifica se é valido
            return decode;
        }catch(JWTVerificationException jwtVerificationException){
            throw new RuntimeException(jwtVerificationException.getMessage());
        }
    }

}