package com.mobiauto.api.dto.veiculo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VeiculoInputDTO {
    private Long idVeiculo;
    private String marca;
    private String modelo;
    private String versao;
    private String anoModelo;
}
