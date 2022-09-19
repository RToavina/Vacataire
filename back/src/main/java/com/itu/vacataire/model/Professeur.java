package com.itu.vacataire.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Professeur {
    @Id
    private String username;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @MapsId
    private User identifiant;
    @ManyToMany (fetch = FetchType.EAGER)
    private Set<Matiere> matieres;

    @OneToMany (fetch = FetchType.EAGER)
    private Set<Emargement> emargements;

    private Double tauxHoraire;

    public Professeur(){}

    public Professeur(User identifiant, Set<Matiere> matieres, double tauxHoraire){
        this.identifiant = identifiant;
        this.matieres = matieres;
        this.tauxHoraire = tauxHoraire;
    }

    public Professeur(User identifiant, Set<Matiere> matieres, Set<Emargement> emargements, double tauxHoraire){
        this.identifiant = identifiant;
        this.matieres = matieres;
        this.tauxHoraire = tauxHoraire;
        this.emargements = emargements;
    }
    public Professeur(User identifiant, Set<Matiere> matieres){
        this.identifiant = identifiant;
        this.matieres = matieres;
    }

    public Set<Matiere> getMatieres() {return matieres;}

    public void setMatieres(Set<Matiere> matieres) {this.matieres = matieres;}

    public Double getTauxHoraire() {return tauxHoraire;}

    public void setTauxHoraire(Double tauxHoraire) {this.tauxHoraire = tauxHoraire;}

    public User getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(User identifiant) {
        this.identifiant = identifiant;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Emargement> getEmargements() {
        return emargements;
    }

    public void setEmargements(Set<Emargement> emargements) {
        this.emargements = emargements;
    }
}
