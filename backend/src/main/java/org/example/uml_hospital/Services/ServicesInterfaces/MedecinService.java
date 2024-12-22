package org.example.uml_hospital.Services.ServicesInterfaces;

import org.example.uml_hospital.Dtos.Request.MedecinRequest;
import org.example.uml_hospital.Dtos.Response.MedecinResponse;
import org.example.uml_hospital.Entities.Medecin;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface MedecinService {
    Medecin creerMedecin(MedecinRequest request);
    void deleteMedecin(long id);
    List<MedecinResponse> getAllMedecins();
    MedecinResponse getMedecin(Long id);
    MedecinResponse UpdateMedecin(Long id , MedecinRequest medecinRequest);
    Medecin getMedecinEntityById(Long id);
}
