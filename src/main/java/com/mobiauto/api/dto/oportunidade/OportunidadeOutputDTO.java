package com.mobiauto.api.dto.oportunidade;

import com.mobiauto.api.dto.atendimento.AtendimentoDadosBasicosOutputDTO;
import com.mobiauto.api.dto.atendimento.AtendimentoOutputDTO;
import com.mobiauto.api.dto.cliente.ClienteDadosBasicosOutputDTO;
import com.mobiauto.api.dto.cliente.ClienteOutputDTO;
import com.mobiauto.api.dto.revenda.RevendaDadosBasicosOutputDTO;
import com.mobiauto.api.dto.revenda.RevendaOutputDTO;
import com.mobiauto.api.dto.usuario.UsuarioDadosBasicosOutputDTO;
import com.mobiauto.api.dto.usuario.UsuarioOutputDTO;
import com.mobiauto.api.dto.veiculo.VeiculoDadosBasicosOutputDTO;
import com.mobiauto.api.dto.veiculo.VeiculoOutputDTO;
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

    private RevendaDadosBasicosOutputDTO revenda;
    private ClienteDadosBasicosOutputDTO cliente;
    private VeiculoDadosBasicosOutputDTO veiculo;
    private UsuarioDadosBasicosOutputDTO usuario;
    private AtendimentoDadosBasicosOutputDTO atendimento;

}
