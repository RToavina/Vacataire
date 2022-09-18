package com.itu.vacataire.services.Interfaces;

import com.itu.vacataire.model.Professeur;

import java.util.List;

public interface IProfesseurService {

    List<Professeur> getAllProfesseur();

    Professeur getProfesseurById(String id);

    Professeur addProfesseur(Professeur professeur);

    void deleteProfesseurById(String id);

}
