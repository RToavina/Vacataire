package com.itu.vacataire.services;

import com.itu.vacataire.model.Emargement;
import com.itu.vacataire.model.Matiere;
import com.itu.vacataire.model.Professeur;
import com.itu.vacataire.payload.request.EmargementRequest;
import com.itu.vacataire.repositories.EmargementRepository;
import com.itu.vacataire.services.Interfaces.IEmargementService;
import com.itu.vacataire.services.Interfaces.IMatiereService;
import com.itu.vacataire.services.Interfaces.IProfesseurService;
import com.itu.vacataire.utils.CalendarUtils;
import com.itu.vacataire.utils.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EmargementService implements IEmargementService {

    @Autowired
    EmargementRepository emargementRepository;

    IProfesseurService professeurService;

    IMatiereService matiereService;

    public EmargementService(ProfesseurService professeurService, MatiereService matiereService) {
        this.professeurService = professeurService;
        this.matiereService = matiereService;
    }


    @Override
    public List<Emargement> findAllByMonth(Date date) {
        return null;
    }

    @Override
    public List<Emargement> findAll() {
        return emargementRepository.findAllByDoneIsFalse();
    }

    @Override
    public List<Emargement> findAllByProfesseur(String username) {
        return emargementRepository.findAllByDoneIsFalseAndProfesseur_Identifiant_Username(username);
    }

    @Override
    public Emargement saveEmargement(EmargementRequest emargement) throws HttpException {
        Professeur p = professeurService.findByUsername(emargement.getUsername());
        Matiere m = matiereService.findMatiereByName(emargement.getMatiere());
        if( m == null) {
            throw new HttpException("error");
        }
        Emargement newEmargement = new Emargement();
        newEmargement.setId(emargement.getId());
        newEmargement.setDate(CalendarUtils.stringToLocalDate(emargement.getDate()));
        newEmargement.setDebut(CalendarUtils.stringToLocaTime(emargement.getDebut()));
        newEmargement.setFin(CalendarUtils.stringToLocaTime(emargement.getFin()));
        newEmargement.setMatiere(m);
        newEmargement.setProfesseur(p);
        newEmargement.setDone(false);
        return emargementRepository.save(newEmargement);
    }


}
