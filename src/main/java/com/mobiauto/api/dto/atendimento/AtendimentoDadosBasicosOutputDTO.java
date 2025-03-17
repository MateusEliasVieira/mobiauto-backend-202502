package com.mobiauto.api.dto.atendimento;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AtendimentoDadosBasicosOutputDTO {
    private Long idAtendimento;
    private Date dataAtendimento;
    private String observacoes;
}
