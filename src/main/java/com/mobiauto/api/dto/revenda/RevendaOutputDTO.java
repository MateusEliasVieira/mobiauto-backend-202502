package com.mobiauto.api.dto.revenda;

import com.mobiauto.domain.model.Oportunidade;
import com.mobiauto.domain.model.Usuario;
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
    private List<Usuario> usuarios;
    private List<Oportunidade> oportunidades;

}
