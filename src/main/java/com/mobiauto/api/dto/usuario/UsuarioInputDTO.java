package com.mobiauto.api.dto.usuario;

import com.mobiauto.api.dto.revenda.RevendaIDInputDTO;
import com.mobiauto.domain.enums.RolePerfilUsuario;
import lombok.*;
import org.hibernate.validator.Email;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UsuarioInputDTO {

    private Long idUsuario;
    private String nome;
    @Email
    private String email;
    private String senha;
    private RevendaIDInputDTO revenda;
    private RolePerfilUsuario perfil;

}
