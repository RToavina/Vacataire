package com.itu.vacataire.services.Interfaces;

import com.itu.vacataire.model.Matiere;

import java.util.List;

public interface IMatiereService {

    Matiere getMatierebyId(Long id);

    Matiere findMatiereByName(String name);

    List<Matiere> all();

    Matiere addMatiere(Matiere matiere);

    void deleteMatiere(Long id);

}
