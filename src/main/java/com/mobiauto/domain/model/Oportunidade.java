package com.mobiauto.domain.model;

import com.mobiauto.domain.enums.StatusOportunidade;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Oportunidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOportunidade;
    private StatusOportunidade status;
    private String motivoDaConclusao;
    private Date dataDeAtribuicao;
    private Date dataDeConclusao;

}
