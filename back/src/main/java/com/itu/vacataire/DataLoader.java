package com.itu.vacataire;

import com.itu.vacataire.model.ERole;
import com.itu.vacataire.model.Matiere;
import com.itu.vacataire.model.Professeur;
import com.itu.vacataire.model.Role;
import com.itu.vacataire.payload.request.SignupRequest;
import com.itu.vacataire.repositories.RoleRepository;
import com.itu.vacataire.services.AuthService;
import com.itu.vacataire.services.MatiereService;
import com.itu.vacataire.services.ProfesseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Transactional
public class DataLoader implements ApplicationRunner {

    @Autowired
    private MatiereService matiereService;

    @Autowired
    private ProfesseurService professeurService;


    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthService authService;

    private Matiere svt = new Matiere("SVT");
    private Matiere pc = new Matiere("PC");
    private Matiere info = new Matiere("INFO");

    private Professeur prof1, prof2, prof3;

    public void initMatiere() {
        svt = matiereService.addMatiere(svt);
        pc = matiereService.addMatiere(pc);
        info = matiereService.addMatiere(info);
    }

    public void initProfesseur() {
        prof1 = professeurService.addProfesseur(new Professeur("nom1", "prenom1", List.of(svt, pc), 1000));
        prof2 = professeurService.addProfesseur(new Professeur("nom2", "prenom2", List.of(pc), 1500));
        prof3 = professeurService.addProfesseur(new Professeur("nom3", "prenom3", List.of(info), 1250));
    }

    public void initRole() {
        this.roleRepository.save(new Role(ERole.ROLE_ADMIN));
        this.roleRepository.save(new Role(ERole.ROLE_USER));
    }

    public void initAdminUser() {
        this.authService.registerUser(new SignupRequest("admin",
                "admin@test.com",
                "admin",
                "Admin",
                "admin",
                Set.of("admin")));
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.initMatiere();
        this.initProfesseur();
        this.initRole();
        this.initAdminUser();
    }
}
