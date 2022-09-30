package com.itu.vacataire.repositories;

import com.itu.vacataire.model.Professeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfesseurRepository extends JpaRepository<Professeur, Long> {
    Optional<Professeur> findFirstByIdentifiant_Username(String username);
}
