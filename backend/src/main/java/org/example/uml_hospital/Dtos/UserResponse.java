package org.example.uml_hospital.Dtos;

import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String role;
}
