package org.example.uml_hospital.Controllers;

import org.example.uml_hospital.Dtos.Request.PatientRequest;
import org.example.uml_hospital.Dtos.Response.PatientResponse;
import org.example.uml_hospital.Entities.Patient;
import org.example.uml_hospital.Entities.User;
import org.example.uml_hospital.Services.ServicesInterfaces.PatientService;
import org.example.uml_hospital.Utils.CurrentUserUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PatientController {

    private final PatientService patientService;
    private final CurrentUserUtil currentUserUtil;


    public PatientController(PatientService patientService, CurrentUserUtil currentUserUti){
        this.currentUserUtil=currentUserUti;
        this.patientService=patientService;
    }

    @GetMapping("/directeur/patients")
    public ResponseEntity<List<PatientResponse>> getPatients() {
        List<PatientResponse> patients = patientService.getAllPatients();
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/patient/{id}")
    public ResponseEntity<?> getPatient(@PathVariable Long id) {
        try {
            User currentUser = currentUserUtil.getCurrentUser();

            Patient patient = patientService.getPatientEntityById(id);

            if (!currentUser.getId().equals(patient.getUser().getId())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Vous n'êtes pas autorisé à voir cet utilisateur.");
            }
            PatientResponse patientResponse = patientService.getPatient(id);
            return ResponseEntity.ok(patientResponse);
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne : " + ex.getMessage());
        }
    }


    @DeleteMapping("/directeur/supprimer-patient/{id}")
    public ResponseEntity<?> supprimerPatient(@PathVariable Long id) {
        try {
            patientService.deletePatient(id);
            return ResponseEntity.ok("Patient supprimé avec succès !");
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne : " + ex.getMessage());
        }
    }

    @PutMapping("/directeur/update-patient/{id}")
    public ResponseEntity<?> updatePatient(@PathVariable Long id, @RequestBody PatientRequest patientRequest) {
        try {
            PatientResponse updatedPatient = patientService.UpdatePatient(id, patientRequest);
            return ResponseEntity.ok(updatedPatient);
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne : " + ex.getMessage());
        }
    }


    @PutMapping("/patient/update-patient/{id}")
    public ResponseEntity<?> updatePatientParLuiMeme(@PathVariable Long id, @RequestBody PatientRequest patientRequest) {
        try {
            User currentUser = currentUserUtil.getCurrentUser();

            Patient patient = patientService.getPatientEntityById(id);

            if (!currentUser.getId().equals(patient.getUser().getId())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Vous n'êtes pas autorisé à voir cet utilisateur.");
            }
            PatientResponse updatedPatient = patientService.UpdatePatient(id, patientRequest);
            return ResponseEntity.ok(updatedPatient);
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne : " + ex.getMessage());
        }
    }
}
