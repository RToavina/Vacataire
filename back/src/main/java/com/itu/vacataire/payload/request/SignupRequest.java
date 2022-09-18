package com.itu.vacataire.payload.request;

import com.itu.vacataire.model.Matiere;

import java.util.List;
import java.util.Set;

public class SignupRequest {

    private String username;
    private String email;
    private String password;
    private String nom;
    private String prenom;

    private String phoneNumber;
    private Set<String> roles;

    private List<String> matieres;

    public SignupRequest(String username, String email, String password, String nom, String prenom, String phoneNumber, Set<String> roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public List<String> getMatieres() {
        return matieres;
    }

    public void setMatieres(List<String> matieres) {
        this.matieres = matieres;
    }
}
