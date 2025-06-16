package com.celc.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.celc.api.models.Debitos;
import com.celc.api.models.Trabalhador;

public interface DebitosRepository extends JpaRepository<Debitos, Long> {
    List<Debitos> findByTrabalhador(Trabalhador trabalhador);
}