package com.mobiauto.api.dto.oportunidade;

import com.mobiauto.api.dto.atendimento.AtendimentoIDInputDTO;
import com.mobiauto.api.dto.cliente.ClienteInputDTO;
import com.mobiauto.api.dto.revenda.RevendaIDInputDTO;
import com.mobiauto.api.dto.usuario.UsuarioIDInputDTO;
import com.mobiauto.api.dto.veiculo.VeiculoIDInputDTO;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OportunidadeCriarInputDTO {
    private Long idOportunidade;
    private RevendaIDInputDTO revenda;
    private ClienteInputDTO cliente;
    private VeiculoIDInputDTO veiculo;
    private UsuarioIDInputDTO usuario;
    //private AtendimentoIDInputDTO atendimento;
}
