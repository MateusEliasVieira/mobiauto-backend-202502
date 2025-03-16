package com.mobiauto.api.dto.oportunidade;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OportunidadeTransferenciaInputDTO {
    private Long idOportunidade;
    private Long idUsuarioRecebedorDaOportunidade;
}
