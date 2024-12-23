package org.example.uml_hospital.Dtos.Request;

import lombok.Data;

import java.util.Date;

@Data
public class PharmacienRequest {
    private String nom;
    private String prenom;
    private String sexe;
    private Date dateNaissance;
    private String telephone;
    private String email;
}
