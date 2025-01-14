package org.example.uml_hospital.Controllers;

import org.example.uml_hospital.Dtos.Request.ConsultationRequest;
import org.example.uml_hospital.Dtos.Response.ConsultationResponse;
import org.example.uml_hospital.Dtos.Response.PatientResponse;
import org.example.uml_hospital.Entities.Medecin;
import org.example.uml_hospital.Entities.Patient;
import org.example.uml_hospital.Entities.User;
import org.example.uml_hospital.Services.ServicesInterfaces.ConsultationService;
import org.example.uml_hospital.Services.ServicesInterfaces.MedecinService;
import org.example.uml_hospital.Services.ServicesInterfaces.PatientService;
import org.example.uml_hospital.Utils.CurrentUserUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ConsultationController {

    private final ConsultationService consultationService;
    private final CurrentUserUtil currentUserUtil;
    private final PatientService patientService;
    private final MedecinService medecinService;

    public ConsultationController(ConsultationService consultationService, CurrentUserUtil currentUserUtil, PatientService patientService,MedecinService medecinService) {
        this.consultationService = consultationService;
        this.currentUserUtil = currentUserUtil;
        this.patientService=patientService;
        this.medecinService=medecinService;
    }


    @GetMapping("/directeur/consultations")
    public ResponseEntity<List<ConsultationResponse>> getConsultation() {
        List<ConsultationResponse> consultationResponses = consultationService.getAllConsultations();
        return ResponseEntity.ok(consultationResponses);
    }

    @GetMapping("/patient/{id}/consultations")
    public ResponseEntity<?> getConsultationByPatient(@PathVariable Long id) {
        User currentUser=currentUserUtil.getCurrentUser();
        Patient patient= patientService.getPatientEntityById(id);
        if (!currentUser.getId().equals(patient.getUser().getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Vous n'êtes pas autorisé à voir les conultations de cet utilisateur.");
        }
        List<ConsultationResponse> consultationResponses = consultationService.getConsultationsByPatient(patient);
        return ResponseEntity.ok(consultationResponses);
    }

    @GetMapping("/medecin/{id}/consultations")
    public ResponseEntity<?> getConsultationByMedecin(@PathVariable Long id) {
        User currentUser=currentUserUtil.getCurrentUser();
        Medecin medecin= medecinService.getMedecinEntityById(id);
        if (!currentUser.getId().equals(medecin.getUser().getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Vous n'êtes pas autorisé à voir les conultations de cet utilisateur.");
        }
        List<ConsultationResponse> consultationResponses = consultationService.getConsultationsByMedecin(medecin);
        return ResponseEntity.ok(consultationResponses);
    }

    @PostMapping("/patient/creer-consultation")
    public ResponseEntity<ConsultationResponse> createConsultation(@RequestBody ConsultationRequest request) {
        try {
            // Vérification des paramètres d'entrée
            if (request.getMotif() == null || request.getMotif().isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
            if (request.getTypeConsultation() == null || request.getTypeConsultation().isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
            if (request.getDateConsultation() == null) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }

            // Appel du service pour créer la consultation
            ConsultationResponse response = consultationService.createConsultation(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (Exception e) {
            // Log l'erreur pour des diagnostics plus détaillés
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/medecin/supprimer-consultation/{id}")
    public ResponseEntity<?> supprimerConsulation(@PathVariable Long id) {
        try {
            consultationService.deleteConsultation(id);
            return ResponseEntity.ok("Consultation supprimé avec succès !");
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne : " + ex.getMessage());
        }
    }
}
