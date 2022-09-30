package com.itu.vacataire.services.Interfaces;

import com.itu.vacataire.model.Emargement;
import com.itu.vacataire.payload.request.EmargementRequest;
import com.itu.vacataire.utils.HttpException;

import java.util.Date;
import java.util.List;

public interface IEmargementService {
    List<Emargement> findAllByMonth(Date date);
    List<Emargement> findAll();

    List<Emargement> findAllByProfesseur(String username);

    Emargement saveEmargement(EmargementRequest emargement) throws HttpException;
}
