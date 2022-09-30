package itu.mbds.vacataire.models;

import java.util.List;

public class ProfesseurResponse {
    public String username;
    public String email;
    public String nom;
    public String prenom;
    public List<String> roles;
    public List<Matiere> matieres;
    public List<EmargementString> emargements;
    public double tauxHoraire;
}
