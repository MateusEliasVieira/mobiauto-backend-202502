package com.mobiauto.api.mapper.usuario;

import com.mobiauto.api.dto.usuario.UsuarioAtualizacaoInputDTO;
import com.mobiauto.api.dto.usuario.UsuarioInputDTO;
import com.mobiauto.api.dto.usuario.UsuarioOutputDTO;
import com.mobiauto.domain.model.Usuario;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class UsuarioMapeador {

    public static Usuario converterUsuarioEntradaDTOEmUsuario(UsuarioInputDTO usuarioEntradaDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(usuarioEntradaDTO, Usuario.class);
    }

    public static Usuario converterUsuarioUpdateEntradaDTOEmUsuario(UsuarioAtualizacaoInputDTO usuarioAtualizacaoEntradaDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(usuarioAtualizacaoEntradaDTO, Usuario.class);
    }

    public static UsuarioOutputDTO converterUsuarioEmUsuarioSaidaDTO(Usuario usuario){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(usuario, UsuarioOutputDTO.class);
    }

    public static List<UsuarioOutputDTO> converterListaUsuarioEmListaUsuarioSaidaDTO(List<Usuario> listaUsuario){
        ModelMapper modelMapper = new ModelMapper();
        List<UsuarioOutputDTO> listaUsuarioSaidaDTO = new ArrayList<>();
        for(Usuario usuario:listaUsuario){
            UsuarioOutputDTO usuarioSaidaDTO = modelMapper.map(usuario, UsuarioOutputDTO.class);
            listaUsuarioSaidaDTO.add(usuarioSaidaDTO);
        }
        return listaUsuarioSaidaDTO;
    }
}
