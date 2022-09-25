package itu.mbds.vacataire.models;

public class EmargementRequest {

    private Long id;
    private String date;

    @Override
    public String toString() {
        return "EmargementRequest{" +
                "id=" + id +
                ", date=" + date +
                ", username='" + username + '\'' +
                ", debut=" + debut +
                ", fin=" + fin +
                ", matiere='" + matiere + '\'' +
                ", done=" + done +
                '}';
    }

    public EmargementRequest(Long id, String date, String username, String debut, String fin, String matiere, boolean done) {
        this.id = id;
        this.date = date;
        this.username = username;
        this.debut = debut;
        this.fin = fin;
        this.matiere = matiere;
        this.done = done;
    }

    private String username;

    private String debut;

    private String fin;

    private String matiere;

    private boolean done;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDebut() {
        return debut;
    }

    public void setDebut(String debut) {
        this.debut = debut;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
