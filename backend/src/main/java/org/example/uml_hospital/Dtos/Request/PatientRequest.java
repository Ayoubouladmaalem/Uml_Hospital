package org.example.uml_hospital.Dtos.Request;


import lombok.Data;

import java.util.Date;

@Data
public class PatientRequest {
    private String nom;
    private String prenom;
    private float poids;
    private String sexe;
    private String telephone;
    private Date dateNaissance;
    private String email;
}
