package com.itu.vacataire.repositories;

import com.itu.vacataire.model.Professeur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesseurRepository extends JpaRepository<Professeur, Long> {

    Professeur findFirstByNomAndPrenom(String nom, String prenom);

}
