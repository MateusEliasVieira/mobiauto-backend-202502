package com.mobiauto.api.dto.atendimento;

import com.mobiauto.api.dto.oportunidade.OportunidadeIDInputDTO;
import com.mobiauto.api.dto.usuario.UsuarioIDInputDTO;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AtendimentoOportunidadeInputDTO {
    private UsuarioIDInputDTO usuario;
    private OportunidadeIDInputDTO oportunidade;
}
