package com.mobiauto.api.dto.usuario;

import com.mobiauto.api.dto.atendimento.AtendimentoDadosBasicosOutputDTO;
import com.mobiauto.api.dto.oportunidade.OportunidadeDadosBasicosOutputDTO;
import com.mobiauto.api.dto.revenda.RevendaDadosBasicosOutputDTO;
import com.mobiauto.domain.enums.RolePerfilUsuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioOutputDTO {

    private Long idUsuario;
    private String nome;
    private String email;
    private RolePerfilUsuario perfil;
    private RevendaDadosBasicosOutputDTO revenda;
    private List<OportunidadeDadosBasicosOutputDTO> oportunidades;
    private List<AtendimentoDadosBasicosOutputDTO> atendimentos;

}
