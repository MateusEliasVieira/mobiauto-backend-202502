package com.mobiauto.api.dto.cliente;

import com.mobiauto.api.dto.oportunidade.OportunidadeDadosBasicosOutputDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClienteOutputDTO {
    private Long idCliente;
    private String nome;
    private String email;
    private String telefone;
    private List<OportunidadeDadosBasicosOutputDTO> oportunidades;
}
