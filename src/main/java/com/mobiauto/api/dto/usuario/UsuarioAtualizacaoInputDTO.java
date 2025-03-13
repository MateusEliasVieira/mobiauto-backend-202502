package com.mobiauto.api.dto.usuario;

import com.mobiauto.domain.enums.RolePerfilUsuario;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UsuarioAtualizacaoInputDTO {

    private Integer idUsuario;
    private String nome;
    private String email;
    private String senha;
    private RolePerfilUsuario perfil;
}
