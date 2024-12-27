package org.example.uml_hospital.Dtos;

import org.example.uml_hospital.Dtos.Response.*;
import org.example.uml_hospital.Entities.*;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public MedecinResponse convertToMedecinResponse(Medecin medecin) {
        MedecinResponse response = new MedecinResponse();
        response.setId(String.valueOf(medecin.getId()));
        response.setNom(medecin.getUser().getNom());
        response.setPrenom(medecin.getUser().getPrenom());
        response.setSexe(medecin.getUser().getSexe());
        response.setDateNaissance(medecin.getUser().getDateNaissance());
        response.setTelephone(medecin.getUser().getTelephone());
        response.setEmail(medecin.getUser().getEmail());
        response.setSpecialite(medecin.getSpecialite());
        return response;
    }

    public PharmacienResponse convertToPharmacienResponse(Pharmacien pharmacien) {
        PharmacienResponse response = new PharmacienResponse();
        response.setId(String.valueOf(pharmacien.getId()));
        response.setNom(pharmacien.getUser().getNom());
        response.setPrenom(pharmacien.getUser().getPrenom());
        response.setSexe(pharmacien.getUser().getSexe());
        response.setDateNaissance(pharmacien.getUser().getDateNaissance());
        response.setTelephone(pharmacien.getUser().getTelephone());
        response.setEmail(pharmacien.getUser().getEmail());
        return response;
    }


    public SecretaireResponse convertToSecretaireResponse(Secretaire secretaire) {
        SecretaireResponse response = new SecretaireResponse();
        response.setId(String.valueOf(secretaire.getId()));
        response.setNom(secretaire.getUser().getNom());
        response.setPrenom(secretaire.getUser().getPrenom());
        response.setSexe(secretaire.getUser().getSexe());
        response.setDateNaissance(secretaire.getUser().getDateNaissance());
        response.setTelephone(secretaire.getUser().getTelephone());
        response.setEmail(secretaire.getUser().getEmail());
        return response;
    }

    public PatientResponse convertToPatientResponse(Patient patient) {
        PatientResponse response = new PatientResponse();
        response.setId(String.valueOf(patient.getId()));
        response.setNom(patient.getUser().getNom());
        response.setPrenom(patient.getUser().getPrenom());
        response.setSexe(patient.getUser().getSexe());
        response.setDateNaissance(patient.getUser().getDateNaissance());
        response.setTelephone(patient.getUser().getTelephone());
        response.setEmail(patient.getUser().getEmail());
        response.setPoids(patient.getPoids());

        return response;
    }

    public ConsultationResponse convertToConsultationResponse(Consultation consultation) {
        ConsultationResponse response = new ConsultationResponse();
        response.setId(consultation.getId());
        response.setMotif(consultation.getMotif());
        response.setTypeConsultation(consultation.getTypeConsultation());
        response.setDateConsultation(consultation.getDateConsultation());

        if (consultation.getMedecin() != null) {
            response.setMedecinName(consultation.getMedecin().getUser().getNom() + " " +
                    consultation.getMedecin().getUser().getPrenom());
        }
        if (consultation.getPatient() != null) {
            response.setPatientName(consultation.getPatient().getUser().getNom() + " " +
                    consultation.getPatient().getUser().getPrenom());
        }

        return response;
    }

}
