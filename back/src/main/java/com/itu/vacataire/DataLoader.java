package com.itu.vacataire;

import com.itu.vacataire.model.*;
import com.itu.vacataire.payload.request.SignupRequest;
import com.itu.vacataire.repositories.EmargementRepository;
import com.itu.vacataire.repositories.RoleRepository;
import com.itu.vacataire.services.AuthService;
import com.itu.vacataire.services.MatiereService;
import com.itu.vacataire.services.ProfesseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
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

    @Autowired
    private EmargementRepository emargementRepository;

    private Matiere svt = new Matiere("SVT");
    private Matiere pc = new Matiere("PC");
    private Matiere info = new Matiere("INFO");

    private User user1, user2, user3, admin;
    Professeur p1, p2, p3, pAdmin;

    private Set<Emargement> emargements;

    public void initMatiere() {
        svt = matiereService.addMatiere(svt);
        pc = matiereService.addMatiere(pc);
        info = matiereService.addMatiere(info);
    }

    public void initEmargement() {
        emargements = new HashSet<>();
        emargements.add(emargementRepository.save(new Emargement(LocalDate.of(2022, Month.SEPTEMBER, 8), LocalTime.parse("12:32:22",
                DateTimeFormatter.ISO_TIME),  LocalTime.parse("13:32:22",
                DateTimeFormatter.ISO_TIME), svt,pAdmin, false)));
        emargements.add(emargementRepository.save(new Emargement(LocalDate.of(2022, Month.SEPTEMBER, 8), LocalTime.parse("14:32:22",
                DateTimeFormatter.ISO_TIME),  LocalTime.parse("14:32:22",
                DateTimeFormatter.ISO_TIME), pc,pAdmin, false)));
    }

    public void initUser() {
        user1 = this.authService.registerUser(new SignupRequest("user1",
                "user1@test.com",
                "user1",
                "User",
                "user1",
                "00000000",null
              ));
        user2 = this.authService.registerUser(new SignupRequest("user2",
                "user2@test.com",
                "user2",
                "User",
                "user2",
                "00000000",null
        ));
        user3 = this.authService.registerUser(new SignupRequest("user3",
                "user3@test.com",
                "user3",
                "User",
                "user3",
                "00000000",null
        ));
        p1 = professeurService.addProfesseur(new Professeur(user1, Set.of(svt, pc), 1000.0));
        p2 = professeurService.addProfesseur(new Professeur(user2, Set.of(pc), 1500.0));
        p3 = professeurService.addProfesseur(new Professeur(user3, Set.of(info), 1250.0));
    }

    public void initRole() {
        this.roleRepository.save(new Role(ERole.ROLE_ADMIN));
        this.roleRepository.save(new Role(ERole.ROLE_USER));
        this.roleRepository.save(new Role(ERole.ROLE_MODERATOR));
    }

    public void initAdminUser() {
       admin = this.authService.registerUser(new SignupRequest("admin",
                "admin@test.com",
                "admin",
                "Admin",
                "admin",
                "00000000",
                Set.of("admin")));
        pAdmin = professeurService.addProfesseur(new Professeur(admin, Set.of(svt, pc, info), null));

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.initRole();
        this.initMatiere();
        this.initAdminUser();
        this.initUser();
        this.initEmargement();
    }
}
