package com.itu.vacataire.services;

import com.itu.vacataire.model.Matiere;
import com.itu.vacataire.repositories.MatiereRepository;
import com.itu.vacataire.services.Interfaces.IMatiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatiereService implements IMatiereService {

    @Autowired
    private MatiereRepository matiereRepository;

    @Override
    public Matiere getMatierebyId(Long id) {
        return matiereRepository.findById(id).orElse(null);
    }

    @Override
    public Matiere addMatiere(Matiere matiere) {
        if(matiere == null){
          throw new IllegalArgumentException();
        }
        return matiereRepository.save(matiere);
    }

    @Override
    public void deleteMatiere(Long id) {

        matiereRepository.deleteById(id);
    }

}
