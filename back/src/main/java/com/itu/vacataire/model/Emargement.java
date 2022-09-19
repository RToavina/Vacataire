package com.itu.vacataire.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Emargement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private LocalDate date;

    public Emargement(LocalDate date, LocalTime debut, LocalTime fin, Matiere matiere, boolean isDone) {
        this.date = date;
        this.debut = debut;
        this.fin = fin;
        this.matiere = matiere;
        this.done = isDone;
    }

    @NotNull
    private LocalTime debut;

    @NotNull
    private LocalTime fin;

    @NotNull
    @OneToOne
    private Matiere matiere;

    @NotNull
    private boolean done;

    public Emargement(){}

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getDebut() {
        return debut;
    }

    public void setDebut(LocalTime debut) {
        this.debut = debut;
    }

    public LocalTime getFin() {
        return fin;
    }

    public void setFin(LocalTime fin) {
        this.fin = fin;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
