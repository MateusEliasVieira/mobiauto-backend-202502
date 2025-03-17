package com.mobiauto.api.dto.revenda;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RevendaDadosBasicosOutputDTO {
    private Long idRevenda;
    private String cnpj;
    private String nomeSocial;
}
