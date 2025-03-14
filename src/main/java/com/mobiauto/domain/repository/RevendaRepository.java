package com.mobiauto.domain.repository;

import com.mobiauto.domain.model.Revenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RevendaRepository extends JpaRepository <Revenda, Long> {

    public Optional<Revenda> findByCnpj(String cnpj);
}
