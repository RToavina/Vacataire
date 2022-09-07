package com.itu.vacataire.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Matiere {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String nomMatiere;

    @ManyToMany (mappedBy = "matieres")
    private List<Professeur> professeurs;

    public List<Professeur> getProfesseurs() {
        return professeurs;
    }

    public Matiere(){}

    public Matiere(String nomMatiere){
        this.nomMatiere = nomMatiere;
    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getNomMatiere() {return nomMatiere;}

    public void setNomMatiere(String nomMatiere) {this.nomMatiere = nomMatiere;}
}
