package com.mobiauto.api.dto.atendimento;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AtendimentoDadosBasicosOutputDTO {
    private Long idAtendimento;
    private LocalDateTime dataAtendimento;
    private String observacoes;
}
