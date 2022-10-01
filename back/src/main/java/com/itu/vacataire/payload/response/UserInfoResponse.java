package com.itu.vacataire.payload.response;

import java.util.List;

public class UserInfoResponse {
    private String username;
    private String email;

    private String phoneNumber;

    private String nom;

    private String prenom;

    private List<String> roles;

    public UserInfoResponse(String username, String email, String nom, String prenom, String phoneNumber, List<String> roles) {
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.nom = nom;
        this.prenom = prenom;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getRoles() {
        return roles;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
