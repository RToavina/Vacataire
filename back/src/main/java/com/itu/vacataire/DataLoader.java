package com.itu.vacataire;

import com.itu.vacataire.model.*;
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
    private User user1, user2, user3;

    public void initMatiere() {
        svt = matiereService.addMatiere(svt);
        pc = matiereService.addMatiere(pc);
        info = matiereService.addMatiere(info);
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
        prof1 = professeurService.addProfesseur(new Professeur( user1, Set.of(svt, pc), 1000));
        prof2 = professeurService.addProfesseur(new Professeur(user2, Set.of(pc), 1500));
        prof3 = professeurService.addProfesseur(new Professeur(user3, Set.of(info), 1250));
    }

    public void initRole() {
        this.roleRepository.save(new Role(ERole.ROLE_ADMIN));
        this.roleRepository.save(new Role(ERole.ROLE_USER));
        this.roleRepository.save(new Role(ERole.ROLE_MODERATOR));
    }

    public void initAdminUser() {
        this.authService.registerUser(new SignupRequest("admin",
                "admin@test.com",
                "admin",
                "Admin",
                "admin",
                "00000000",
                Set.of("admin")));
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.initRole();
        this.initAdminUser();
        this.initMatiere();
        this.initUser();
    }
}
