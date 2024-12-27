package org.example.uml_hospital.Services.ServicesInterfaces;

import org.example.uml_hospital.Dtos.Request.ConsultationRequest;
import org.example.uml_hospital.Dtos.Response.ConsultationResponse;
import org.example.uml_hospital.Entities.Medecin;
import org.example.uml_hospital.Entities.Patient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ConsultationService {
    ConsultationResponse createConsultation(ConsultationRequest request);
    void deleteConsultation(Long id);
    List<ConsultationResponse> getAllConsultations();
    List<ConsultationResponse> getConsultationsByPatient(Patient patient);
    List<ConsultationResponse> getConsultationsByMedecin(Medecin medecin);
}
