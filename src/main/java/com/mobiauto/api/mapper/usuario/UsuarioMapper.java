package com.mobiauto.api.mapper.usuario;

import com.mobiauto.api.dto.usuario.UsuarioAtualizacaoInputDTO;
import com.mobiauto.api.dto.usuario.UsuarioInputDTO;
import com.mobiauto.api.dto.usuario.UsuarioOutputDTO;
import com.mobiauto.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsuarioMapper {

    private final ModelMapper modelMapper;

    public UsuarioMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public Usuario converterUsuarioInputDTOEmUsuario(UsuarioInputDTO usuarioEntradaDTO){
        return modelMapper.map(usuarioEntradaDTO, Usuario.class);
    }

    public Usuario converterUsuarioUpdateInputDTOEmUsuario(UsuarioAtualizacaoInputDTO usuarioAtualizacaoEntradaDTO){
        return modelMapper.map(usuarioAtualizacaoEntradaDTO, Usuario.class);
    }

    public UsuarioOutputDTO converterUsuarioEmUsuarioOutputDTO(Usuario usuario){
        return modelMapper.map(usuario, UsuarioOutputDTO.class);
    }

    public List<UsuarioOutputDTO> converterListaUsuarioEmListaUsuarioOutputDTO(List<Usuario> listaUsuario){
        List<UsuarioOutputDTO> listaUsuarioSaidaDTO = new ArrayList<>();
        for(Usuario usuario:listaUsuario){
            UsuarioOutputDTO usuarioSaidaDTO = modelMapper.map(usuario, UsuarioOutputDTO.class);
            listaUsuarioSaidaDTO.add(usuarioSaidaDTO);
        }
        return listaUsuarioSaidaDTO;
    }
}
