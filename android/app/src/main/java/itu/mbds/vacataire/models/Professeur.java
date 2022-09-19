package itu.mbds.vacataire.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Professeur {
    public String username;
    public String email;
    public String nom;
    public String prenom;
    public List<String> roles;
    public List<Matiere> matieres;
    public List<Emargement> emargements;
    public double tauxHoraire;

    public Professeur(ProfesseurResponse p) {
        username = p.username;
        email = p.email;
        nom = p.nom;
        prenom = p.prenom;
        roles = p.roles;
        matieres = p.matieres;
        tauxHoraire = p.tauxHoraire;
        emargements = p.emargements.stream().map(emargementString -> {
            Emargement e = new Emargement();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(emargementString.date, formatter);
            e.setDate(date);
            e.setDebut(LocalTime.parse(emargementString.debut));
            e.setFin(LocalTime.parse(emargementString.fin));
            e.setDone(emargementString.done);
            e.setId(emargementString.id);
            e.setMatiere(emargementString.matiere);
            return e;
        }).collect(Collectors.toList());
    }
}
