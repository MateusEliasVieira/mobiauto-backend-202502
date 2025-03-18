package com.mobiauto.api.dto.oportunidade;

import com.mobiauto.domain.enums.StatusOportunidade;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OportunidadeDadosBasicosOutputDTO {
    private Long idOportunidade;
    private StatusOportunidade status;
    private String motivoDaConclusao;
    private LocalDateTime dataDeAtribuicao;
    private LocalDateTime dataDeConclusao;
}
