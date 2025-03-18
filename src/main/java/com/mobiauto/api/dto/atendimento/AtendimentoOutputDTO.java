package com.mobiauto.api.dto.atendimento;

import com.mobiauto.api.dto.oportunidade.OportunidadeDadosBasicosOutputDTO;
import com.mobiauto.api.dto.usuario.UsuarioDadosBasicosOutputDTO;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AtendimentoOutputDTO {
    
    private Long idAtendimento;
    private LocalDateTime dataAtendimento;
    private String observacoes;

    private UsuarioDadosBasicosOutputDTO usuario;
    private OportunidadeDadosBasicosOutputDTO oportunidade;

}
