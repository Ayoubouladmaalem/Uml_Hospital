package org.example.uml_hospital.Services.ServicesInterfaces;

import org.example.uml_hospital.Dtos.SecretaireRequest;
import org.example.uml_hospital.Entities.Secretaire;
import org.springframework.stereotype.Service;


@Service
public interface SecretaireService {
    Secretaire creerSecretaire(SecretaireRequest request);
}
