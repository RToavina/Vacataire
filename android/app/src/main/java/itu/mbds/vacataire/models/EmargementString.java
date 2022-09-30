package itu.mbds.vacataire.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class EmargementString {
    public Long id;
    public Matiere matiere;

    public String date;

    public String debut;
    public String fin;
    public boolean done;

    public static Emargement toEmargement(EmargementString emargementString) {
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
    }
}
