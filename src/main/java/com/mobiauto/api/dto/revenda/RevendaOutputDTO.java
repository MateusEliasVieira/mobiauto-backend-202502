package com.mobiauto.api.dto.revenda;

import com.mobiauto.api.dto.oportunidade.OportunidadeDadosBasicosOutputDTO;
import com.mobiauto.api.dto.usuario.UsuarioDadosBasicosOutputDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RevendaOutputDTO {// Loja

    private Long idRevenda;
    private String cnpj;
    private String nomeSocial;
    private List<UsuarioDadosBasicosOutputDTO> usuarios;
    private List<OportunidadeDadosBasicosOutputDTO> oportunidades;

}
