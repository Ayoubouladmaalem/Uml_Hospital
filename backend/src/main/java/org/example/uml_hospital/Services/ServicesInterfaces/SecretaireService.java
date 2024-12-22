package org.example.uml_hospital.Services.ServicesInterfaces;

import org.example.uml_hospital.Dtos.Request.SecretaireRequest;
import org.example.uml_hospital.Dtos.Response.PharmacienResponse;
import org.example.uml_hospital.Dtos.Response.SecretaireResponse;
import org.example.uml_hospital.Entities.Medecin;
import org.example.uml_hospital.Entities.Secretaire;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface SecretaireService {
    Secretaire creerSecretaire(SecretaireRequest request);
    void deleteSecretaire(long id);
    List<SecretaireResponse> getAllSecretaires();
    SecretaireResponse getSecretaire(Long id);
    SecretaireResponse updateSecretaire(Long id, SecretaireRequest secretaireRequest);
    Secretaire getSecretaireEntityById(Long id);



}
