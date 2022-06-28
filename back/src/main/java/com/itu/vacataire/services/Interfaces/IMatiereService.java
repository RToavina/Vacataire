package com.itu.vacataire.services.Interfaces;

import com.itu.vacataire.model.Matiere;

public interface IMatiereService {

    Matiere getMatierebyId(Long id);

    Matiere addMatiere(Matiere matiere);

    void deleteMatiere(Long id);

}
