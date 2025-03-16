package com.mobiauto.api.dto.usuario;

import com.mobiauto.domain.enums.RolePerfilUsuario;
import com.mobiauto.domain.model.Atendimento;
import com.mobiauto.domain.model.Oportunidade;
import com.mobiauto.domain.model.Revenda;
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
    private Revenda revenda;
    private List<Oportunidade> oportunidades;
    private List<Atendimento> atendimentos;

}
