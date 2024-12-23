package org.example.uml_hospital.Dtos.Request;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String motDePasse;
}
