package com.mobiauto.api.dto.oportunidade;

import com.mobiauto.domain.model.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OportunidadeCriarInputDTO {
    private Long idOportunidade;
    private Revenda revenda;
    private Cliente cliente;
    private Veiculo veiculo;
    private Usuario usuario;
    private Atendimento atendimento;
}
