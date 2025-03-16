package com.mobiauto.api.dto.oportunidade;

import com.mobiauto.domain.enums.StatusOportunidade;
import com.mobiauto.domain.model.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OportunidadeOutputDTO {

    private Long idOportunidade;
    private StatusOportunidade status;
    private String motivoDaConclusao;
    private Date dataDeAtribuicao;
    private Date dataDeConclusao;

    private Revenda revenda;
    private Cliente cliente;
    private Veiculo veiculo;
    private Usuario usuario;
    private Atendimento atendimento;

}
