package itu.mbds.vacataire.models;

import java.util.List;
import java.util.Set;

public class SignupRequest {
    private String username;
    private String email;
    private String password;
    private String nom;
    private String prenom;
    private String phoneNumber;
    private List<String> matieres;
    private Set<String> roles;

    public SignupRequest(String username, String email, String password, String nom, String prenom, String phoneNumber, List<String> matieres) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.phoneNumber = phoneNumber;
        this.matieres = matieres;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
