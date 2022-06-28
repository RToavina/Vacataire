package com.itu.vacataire;

import com.itu.vacataire.model.Matiere;
import com.itu.vacataire.model.Professeur;
import com.itu.vacataire.services.MatiereService;
import com.itu.vacataire.services.ProfesseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class DataLoader implements ApplicationRunner {

    @Autowired
    private MatiereService matiereService;

    @Autowired
    private ProfesseurService professeurService;


    private Matiere svt = new Matiere("SVT");
    private Matiere pc = new Matiere("PC");
    private Matiere info = new Matiere("INFO");

    private Professeur prof1,prof2,prof3;

    public void initMatiere(){
        svt = matiereService.addMatiere(svt);
        pc = matiereService.addMatiere(pc);
        info = matiereService.addMatiere(info);
    }

    public void initProfesseur(){
        prof1 = professeurService.addProfesseur(new Professeur("nom1","prenom1", List.of(svt,pc),1000));
        prof2 = professeurService.addProfesseur(new Professeur("nom2","prenom2", List.of(pc),1500));
        prof3 = professeurService.addProfesseur(new Professeur("nom3","prenom3", List.of(info),1250));
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.initMatiere();
        this.initProfesseur();
    }
}
