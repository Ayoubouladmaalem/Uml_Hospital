package org.example.uml_hospital.Services.ServicesInterfaces;


import org.example.uml_hospital.Dtos.Request.PharmacienRequest;
import org.example.uml_hospital.Dtos.Response.PharmacienResponse;
import org.example.uml_hospital.Entities.Medecin;
import org.example.uml_hospital.Entities.Pharmacien;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PharmacienService {
    Pharmacien creerPharmacien(PharmacienRequest request);
    void deletePharmacien(long id);
    List<PharmacienResponse> getAllPharmaciens();
    PharmacienResponse getPharmacien(Long id);
    PharmacienResponse updatePharmacien(Long id, PharmacienRequest pharmacienRequest);
    Pharmacien getPharmacienEntityById(Long id);



}
