package com.mobiauto.api.dto.oportunidade;

import com.mobiauto.api.dto.atendimento.AtendimentoDadosBasicosOutputDTO;
import com.mobiauto.api.dto.cliente.ClienteDadosBasicosOutputDTO;
import com.mobiauto.api.dto.revenda.RevendaDadosBasicosOutputDTO;
import com.mobiauto.api.dto.usuario.UsuarioDadosBasicosOutputDTO;
import com.mobiauto.api.dto.veiculo.VeiculoDadosBasicosOutputDTO;
import com.mobiauto.domain.enums.StatusOportunidade;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OportunidadeOutputDTO {

    private Long idOportunidade;
    private StatusOportunidade status;
    private String motivoDaConclusao;
    private LocalDateTime dataDeAtribuicao;
    private LocalDateTime dataDeConclusao;

    private RevendaDadosBasicosOutputDTO revenda;
    private ClienteDadosBasicosOutputDTO cliente;
    private VeiculoDadosBasicosOutputDTO veiculo;
    private UsuarioDadosBasicosOutputDTO usuario;
    private AtendimentoDadosBasicosOutputDTO atendimento;

}
