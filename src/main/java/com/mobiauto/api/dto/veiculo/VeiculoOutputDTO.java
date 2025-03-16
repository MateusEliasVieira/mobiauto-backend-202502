package com.mobiauto.api.dto.veiculo;

import com.mobiauto.domain.model.Oportunidade;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VeiculoOutputDTO {

    private Long idVeiculo;
    private String marca;
    private String modelo;
    private String versao;
    private String anoModelo;

    private List<Oportunidade> oportunidades;
}
