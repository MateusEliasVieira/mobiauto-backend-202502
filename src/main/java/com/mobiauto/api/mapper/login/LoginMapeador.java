package com.mobiauto.api.mapper.login;

import com.mobiauto.api.dto.login.LoginInputDTO;
import com.mobiauto.api.dto.login.LoginOutputDTO;
import com.mobiauto.domain.model.Usuario;
import org.modelmapper.ModelMapper;

public class LoginMapeador {

    public static Usuario mapperLoginInputDTOParaUsuario(LoginInputDTO loginInputDTO) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(loginInputDTO, Usuario.class);
    }

    public static LoginOutputDTO mapperUsuarioParaLoginOutputDTO(Usuario usuario) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(usuario, LoginOutputDTO.class);
    }
}
