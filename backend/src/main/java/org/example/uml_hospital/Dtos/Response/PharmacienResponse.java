package org.example.uml_hospital.Dtos.Response;

import lombok.Data;

import java.util.Date;


@Data
public class PharmacienResponse {
    private String id;
    private String nom;
    private String prenom;
    private String sexe;
    private Date dateNaissance;
    private String telephone;
    private String email;
}
