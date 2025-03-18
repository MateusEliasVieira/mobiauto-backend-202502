package com.mobiauto.api.mapper.login;

import com.mobiauto.api.dto.login.LoginOutputDTO;
import com.mobiauto.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class LoginMapper {

    private final ModelMapper mapper;

    public LoginMapper(ModelMapper modelMapper) {
        this.mapper = modelMapper;
    }

    public LoginOutputDTO converterUsuarioParaLoginOutputDTO(Usuario usuario) {
        return this.mapper.map(usuario, LoginOutputDTO.class);
    }
}
