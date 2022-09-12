package itu.mbds.vacataire.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class Emargement {
    //TODO compléter la classe
    private String matiere;

    private LocalDate date;
    private LocalTime heureDepart;
    private LocalTime heureArrive;

    public Emargement(String matiere, LocalDate date, LocalTime heureDepart, LocalTime heureArrive) {
        this.matiere = matiere;
        this.date = date;
        this.heureDepart = heureDepart;
        this.heureArrive = heureArrive;
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

    public LocalTime getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(LocalTime heureDepart) {
        this.heureDepart = heureDepart;
    }

    public LocalTime getHeureArrive() {
        return heureArrive;
    }

    public void setHeurearrive(LocalTime heureArrive) {
        this.heureArrive = heureArrive;
    }
}
