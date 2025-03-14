package com.mobiauto.api.dto.usuario;

import com.mobiauto.domain.enums.RolePerfilUsuario;
import com.mobiauto.domain.model.Revenda;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioInputDTO {

    private Long idUsuario;
    private String nome;
    private String email;
    private String senha;
    private Revenda revenda;
    private RolePerfilUsuario perfil;

}
