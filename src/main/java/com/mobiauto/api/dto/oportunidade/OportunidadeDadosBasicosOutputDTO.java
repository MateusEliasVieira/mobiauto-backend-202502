package com.mobiauto.api.dto.oportunidade;

import com.mobiauto.domain.enums.StatusOportunidade;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OportunidadeDadosBasicosOutputDTO {
    private Long idOportunidade;
    private StatusOportunidade status;
    private String motivoDaConclusao;
    private Date dataDeAtribuicao;
    private Date dataDeConclusao;
}
