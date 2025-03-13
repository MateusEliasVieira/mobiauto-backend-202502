package com.mobiauto.api.dto.usuario;

import com.mobiauto.domain.enums.RolePerfilUsuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioInputDTO {

    private String nome;
    private String email;
    private String senha;
    private RolePerfilUsuario perfil;

}
