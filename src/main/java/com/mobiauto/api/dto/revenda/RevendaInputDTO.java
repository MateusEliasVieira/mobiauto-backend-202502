package com.mobiauto.api.dto.revenda;

import br.com.caelum.stella.hibernate.validator.CNPJ;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RevendaInputDTO {// Loja

    private Long idRevenda;
    @CNPJ
    private String cnpj;
    private String nomeSocial;

}
