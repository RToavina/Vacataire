package itu.mbds.vacataire.models;

import com.google.gson.annotations.SerializedName;

public class UserRequest {
    @SerializedName("username")
    private String identifiant;

    @SerializedName("password")
    private String password;

    public UserRequest(String identifiant, String password) {
        this.identifiant = identifiant;
        this.password = password;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
