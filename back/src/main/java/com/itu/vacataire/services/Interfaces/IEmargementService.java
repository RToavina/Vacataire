package com.itu.vacataire.services.Interfaces;

import com.itu.vacataire.model.Emargement;

import java.util.Date;
import java.util.List;

public interface IEmargementService {
    List<Emargement> findAllByMonth(Date date);
    List<Emargement> findAll();

    List<Emargement> findAllByProfesseur(String username);
}
