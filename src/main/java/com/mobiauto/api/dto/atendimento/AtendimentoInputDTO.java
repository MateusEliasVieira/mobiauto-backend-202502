package com.mobiauto.api.dto.atendimento;

import com.mobiauto.domain.model.Oportunidade;
import com.mobiauto.domain.model.Usuario;
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
public class AtendimentoInputDTO {

    private Long idAtendimento;
    private String observacoes;

    private Usuario usuario;
    private Oportunidade oportunidade;
}
