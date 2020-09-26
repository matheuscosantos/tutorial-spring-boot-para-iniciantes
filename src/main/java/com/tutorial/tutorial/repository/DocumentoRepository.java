package com.tutorial.tutorial.repository;

import com.tutorial.tutorial.model.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Integer> {
    Optional<Documento> findById(Integer id);
}
