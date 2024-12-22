package org.example.uml_hospital.Services.ServicesInterfaces;

import org.example.uml_hospital.Dtos.MedecinRequest;
import org.example.uml_hospital.Entities.Medecin;
import org.springframework.stereotype.Service;


@Service
public interface MedecinService {
    Medecin creerMedecin(MedecinRequest request);
}
