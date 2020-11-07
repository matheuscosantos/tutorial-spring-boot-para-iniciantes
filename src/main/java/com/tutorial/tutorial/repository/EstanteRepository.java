package com.tutorial.tutorial.repository;

import com.tutorial.tutorial.model.Estante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstanteRepository extends JpaRepository<Estante, Integer> {
}
