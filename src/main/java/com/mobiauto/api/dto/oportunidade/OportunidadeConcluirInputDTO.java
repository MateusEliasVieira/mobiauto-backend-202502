package com.mobiauto.api.dto.oportunidade;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OportunidadeConcluirInputDTO {
    private Long idOportunidade;
    private String motivoDaConclusao;
}
