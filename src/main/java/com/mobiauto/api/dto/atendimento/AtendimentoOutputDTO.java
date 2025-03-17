package com.mobiauto.api.dto.atendimento;

import com.mobiauto.api.dto.oportunidade.OportunidadeDadosBasicosOutputDTO;
import com.mobiauto.api.dto.usuario.UsuarioDadosBasicosOutputDTO;
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

    private UsuarioDadosBasicosOutputDTO usuario;
    private OportunidadeDadosBasicosOutputDTO oportunidade;

}
