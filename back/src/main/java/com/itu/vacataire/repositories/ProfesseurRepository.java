package com.itu.vacataire.repositories;

import com.itu.vacataire.model.Professeur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfesseurRepository extends JpaRepository<Professeur, Long> {
    Optional<Professeur> findFirstByIdentifiant_Username(String username);
}
