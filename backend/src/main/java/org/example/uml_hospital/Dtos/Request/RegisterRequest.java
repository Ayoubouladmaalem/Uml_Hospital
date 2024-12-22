package org.example.uml_hospital.Dtos.Request;

import lombok.Data;

import java.util.Date;

@Data
public class RegisterRequest {
    private String nom;
    private String prenom;
    private String sexe;
    private String telephone;
    private Date dateNaissance;
    private float poids;
    private String email;
    private String motDePasse;
    private String confirmationMotDePasse;
}
