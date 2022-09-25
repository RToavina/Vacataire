package com.itu.vacataire.repositories;

import com.itu.vacataire.model.Emargement;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EmargementRepository extends JpaRepository<Emargement, Long> {
    List<Emargement> findAllByDateBetween(Date start, Date end);

    List<Emargement> findAllByDoneIsFalse();
    List<Emargement> findAllByDoneIsFalseAndProfesseur_Identifiant_Username(String username);
}
