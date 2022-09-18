package com.itu.vacataire.repositories;

import com.itu.vacataire.model.Matiere;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MatiereRepository extends JpaRepository<Matiere, Long> {
    Optional<Matiere> findMatiereByNomMatiere(String nomMatiere);
}
