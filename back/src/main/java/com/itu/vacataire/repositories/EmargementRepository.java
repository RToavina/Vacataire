package com.itu.vacataire.repositories;

import com.itu.vacataire.model.Emargement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface EmargementRepository extends JpaRepository<Emargement, Long> {
    List<Emargement> findAllByDateBetween(Date start, Date end);
    List<Emargement> findAllByDoneIsTrue();
}
