package com.itu.vacataire.repositories;

import com.itu.vacataire.model.Matiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MatiereRepository extends JpaRepository<Matiere, Long> {
    Optional<Matiere> findMatiereByNomMatiere(String nomMatiere);
}
