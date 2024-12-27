package org.example.uml_hospital.Services.ServicesInterfaces;


import org.example.uml_hospital.Dtos.Request.MedecinRequest;
import org.example.uml_hospital.Dtos.Request.PatientRequest;
import org.example.uml_hospital.Dtos.Response.MedecinResponse;
import org.example.uml_hospital.Dtos.Response.PatientResponse;
import org.example.uml_hospital.Entities.Medecin;
import org.example.uml_hospital.Entities.Patient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PatientService {
    void deletePatient(long id);
    List<PatientResponse> getAllPatients();
    PatientResponse getPatient(Long id);
    PatientResponse UpdatePatient(Long id , PatientRequest patientRequest);
    Patient getPatientEntityById(Long id);
}
