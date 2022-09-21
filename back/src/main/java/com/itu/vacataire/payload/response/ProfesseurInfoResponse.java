package com.itu.vacataire.payload.response;

import com.itu.vacataire.model.Emargement;
import com.itu.vacataire.model.Matiere;
import com.itu.vacataire.model.Professeur;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProfesseurInfoResponse extends UserInfoResponse {
    private Set<Matiere> matieres;
    private Set<Emargement> emargements;
    private Double tauxHoraire;

    public ProfesseurInfoResponse(String username, String email, String nom, String prenom, List<String> roles) {
        super(username, email, nom, prenom, roles);
    }

    public ProfesseurInfoResponse(Professeur p) {
        super(p.getIdentifiant().getUsername(),
                p.getIdentifiant().getEmail(),
                p.getIdentifiant().getNom(),
                p.getIdentifiant().getPrenom(),
                p.getIdentifiant().getRoles().stream().map(r -> r.getName().name()).collect(Collectors.toList())
        );
        matieres = p.getMatieres();
        tauxHoraire = p.getTauxHoraire();
        emargements = p.getEmargements();
    }

    public Set<Matiere> getMatieres() {
        return matieres;
    }

    public void setMatieres(Set<Matiere> matieres) {
        this.matieres = matieres;
    }

    public Double getTauxHoraire() {
        return tauxHoraire;
    }

    public void setTauxHoraire(Double tauxHoraire) {
        this.tauxHoraire = tauxHoraire;
    }

    public Set<Emargement> getEmargements() {
        return emargements;
    }

    public void setEmargements(Set<Emargement> emargements) {
        this.emargements = emargements;
    }
}
