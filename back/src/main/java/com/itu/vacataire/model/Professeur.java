package com.itu.vacataire.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
public class Professeur {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nom;

    private String prenom;

    @ManyToMany (fetch = FetchType.EAGER)
    private List<Matiere> matieres;

    private Double tauxHoraire;

    public Professeur(){}

    public Professeur(String nom, String prenom, List<Matiere> matieres, double tauxHoraire){
        this.nom = nom;
        this.prenom = prenom;
        this.matieres = matieres;
        this.tauxHoraire = tauxHoraire;
    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getNom() {return nom;}

    public void setNom(String nom) {this.nom = nom;}

    public String getPrenom() {return prenom;}

    public void setPrenom(String prenom) {this.prenom = prenom;}

    public List<Matiere> getMatieres() {return matieres;}

    public void setMatieres(List<Matiere> matieres) {this.matieres = matieres;}

    public Double getTauxHoraire() {return tauxHoraire;}

    public void setTauxHoraire(Double tauxHoraire) {this.tauxHoraire = tauxHoraire;}
}
