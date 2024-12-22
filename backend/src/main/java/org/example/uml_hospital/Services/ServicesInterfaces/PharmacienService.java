package org.example.uml_hospital.Services.ServicesInterfaces;


import org.example.uml_hospital.Dtos.PharmacienRequest;
import org.example.uml_hospital.Entities.Pharmacien;
import org.springframework.stereotype.Service;

@Service
public interface PharmacienService {
    Pharmacien creerPharmacien(PharmacienRequest request);
}
