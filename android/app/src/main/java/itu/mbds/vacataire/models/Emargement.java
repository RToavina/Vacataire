package itu.mbds.vacataire.models;

import java.time.LocalDate;

public class Emargement {
    //TODO compléter la classe
    private String matiere;

    private LocalDate date;


    public Emargement(String matiere, LocalDate date) {
        this.matiere = matiere;
        this.date = date;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
