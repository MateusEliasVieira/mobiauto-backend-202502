package com.mobiauto.utils;

import com.mobiauto.domain.exception.RegrasDeNegocioException;

public class ValidadorDeSenha {

    public static void isStrong(String senha) {
        int contadorDeCaracteres = 0;
        int contadorDeCaracteresEspeciais = 0;
        int contadorDeNumeros = 0;

        for (int i = 0; i < senha.length(); i++) {
            if (senha.toLowerCase().charAt(i) >= 'a' && senha.toLowerCase().charAt(i) <= 'z') {
                contadorDeCaracteres++;
            } else if (senha.toLowerCase().charAt(i) == '!' || senha.toLowerCase().charAt(i) == '@'
                    || senha.toLowerCase().charAt(i) == '#' || senha.toLowerCase().charAt(i) == '$'
                    || senha.toLowerCase().charAt(i) == '%' || senha.toLowerCase().charAt(i) == '*'
                    || senha.toLowerCase().charAt(i) == '(' || senha.toLowerCase().charAt(i) == ')'
                    || senha.toLowerCase().charAt(i) == '-' || senha.toLowerCase().charAt(i) == '+') {
                contadorDeCaracteresEspeciais++;
            } else if (senha.toLowerCase().charAt(i) == '0' || senha.toLowerCase().charAt(i) == '1'
                    || senha.toLowerCase().charAt(i) == '2' || senha.toLowerCase().charAt(i) == '3'
                    || senha.toLowerCase().charAt(i) == '4' || senha.toLowerCase().charAt(i) == '5'
                    || senha.toLowerCase().charAt(i) == '6' || senha.toLowerCase().charAt(i) == '7'
                    || senha.toLowerCase().charAt(i) == '8' || senha.toLowerCase().charAt(i) == '9') {
                contadorDeNumeros++;
            }
        }

        if (!(contadorDeCaracteres >= 2 && contadorDeCaracteresEspeciais >= 2 && contadorDeNumeros >= 2)) {
            throw new RegrasDeNegocioException(Resposta.SENHA_CADASTRO);
        }

    }
}
