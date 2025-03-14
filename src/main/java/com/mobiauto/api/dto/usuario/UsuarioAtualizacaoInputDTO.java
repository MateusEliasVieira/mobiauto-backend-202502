package com.mobiauto.api.dto.usuario;

import com.mobiauto.domain.enums.RolePerfilUsuario;
import com.mobiauto.domain.model.Revenda;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UsuarioAtualizacaoInputDTO {

    private Long idUsuario;
    private String nome;
    private String email;
    private String senha;
    private Revenda revenda;
    private RolePerfilUsuario perfil;
}
