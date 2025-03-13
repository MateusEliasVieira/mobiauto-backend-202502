package com.mobiauto.domain.model;

import br.com.caelum.stella.hibernate.validator.CNPJ;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Revenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRevenda;
    @Column(unique = true)
    @CNPJ
    private String cnpj;
    private String nomeSocial;
}
