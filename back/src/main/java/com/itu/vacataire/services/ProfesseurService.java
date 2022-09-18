package com.itu.vacataire.services;

import com.itu.vacataire.model.Professeur;
import com.itu.vacataire.repositories.ProfesseurRepository;
import com.itu.vacataire.services.Interfaces.IProfesseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesseurService implements IProfesseurService {

    @Autowired
    private ProfesseurRepository professeurRepository;

    @Override
    public List<Professeur> getAllProfesseur() {
        return professeurRepository.findAll();
    }

    public Professeur getProfesseurById(Long id){
        return professeurRepository.findById(id).orElse(null);
    }

    @Override
    public Professeur addProfesseur(Professeur professeur) {
        if(professeur == null){
            throw new IllegalArgumentException();
        }
        return professeurRepository.save(professeur);
    }

    @Override
    public void deleteProfesseurById(Long id) {
        professeurRepository.deleteById(id);
    }


}
