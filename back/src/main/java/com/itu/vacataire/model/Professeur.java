package com.itu.vacataire.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Professeur {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(fetch = FetchType.EAGER)
    private User identifiant;
    @ManyToMany (fetch = FetchType.EAGER)
    private Set<Matiere> matieres;

    private Double tauxHoraire;

    public Professeur(){}

    public Professeur(User identifiant, Set<Matiere> matieres, double tauxHoraire){
        this.identifiant = identifiant;
        this.matieres = matieres;
        this.tauxHoraire = tauxHoraire;
    }

    public Professeur(User identifiant, Set<Matiere> matieres){
        this.identifiant = identifiant;
        this.matieres = matieres;
    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

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
}
