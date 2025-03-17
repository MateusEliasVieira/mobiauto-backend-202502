package com.mobiauto.api.dto.revenda;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RevendaAddUsuarioInputDTO {
    private Long idRevenda;
    private Long  idUsuario;
}
