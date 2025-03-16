package com.mobiauto.api.dto.atendimento;

import com.mobiauto.domain.model.Oportunidade;
import com.mobiauto.domain.model.Usuario;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AtendimentoOutputDTO {
    
    private Long idAtendimento;
    private Date dataAtendimento;
    private String observacoes;

    private Usuario usuario;
    private Oportunidade oportunidade;

}
