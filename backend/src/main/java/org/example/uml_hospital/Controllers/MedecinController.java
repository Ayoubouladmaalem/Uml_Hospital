package org.example.uml_hospital.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.example.uml_hospital.Dtos.Request.MedecinRequest;
import org.example.uml_hospital.Dtos.Response.MedecinResponse;
import org.example.uml_hospital.Entities.Medecin;
import org.example.uml_hospital.Entities.User;
import org.example.uml_hospital.Services.ServicesInterfaces.MedecinService;
import org.example.uml_hospital.Utils.CurrentUserUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/directeur")
@CrossOrigin(origins = "http://localhost:3000")
public class MedecinController {

    private final MedecinService medecinService;
    private final CurrentUserUtil currentUserUtil;

    public MedecinController(MedecinService medecinService, CurrentUserUtil currentUserUtil) {
        this.medecinService = medecinService;
        this.currentUserUtil = currentUserUtil;
    }
    @PreAuthorize("hasRole('ROLE_DIRECTEUR')")
    @PostMapping("/directeur/creer-medecin")
//    public ResponseEntity<String> creerMedecin(@RequestBody MedecinRequest request) {
//        medecinService.creerMedecin(request);
//        return ResponseEntity.status(HttpStatus.CREATED).body("Médecin créé avec succès !");
//    }
    public ResponseEntity<String> creerMedecin(HttpServletRequest request, @RequestBody MedecinRequest medecinRequest) {
        String token = request.getHeader("Authorization");
        System.out.println("Token received: " + token); // Log token
        medecinService.creerMedecin(medecinRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Médecin créé avec succès !");
    }

    @DeleteMapping("/directeur/supprimer-medecin/{id}")
    public ResponseEntity<?> supprimerMedecin(@PathVariable Long id) {
        try {
            medecinService.deleteMedecin(id);
            return ResponseEntity.ok("Médecin supprimé avec succès !");
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne : " + ex.getMessage());
        }
    }


    @GetMapping("/medecins")
    public ResponseEntity<List<MedecinResponse>> getMedecins() {
        List<MedecinResponse> medecins = medecinService.getAllMedecins();
        return ResponseEntity.ok(medecins);
    }

    @GetMapping("/medecin/{id}")
    public ResponseEntity<?> getMedecin(@PathVariable Long id) {
        try {
            User currentUser = currentUserUtil.getCurrentUser();

            Medecin medecin = medecinService.getMedecinEntityById(id);

            if (!currentUser.getId().equals(medecin.getUser().getId())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Vous n'êtes pas autorisé à voir cet utilisateur.");
            }
            MedecinResponse medecinResponse = medecinService.getMedecin(id);
            return ResponseEntity.ok(medecinResponse);
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne : " + ex.getMessage());
        }
    }


    @PutMapping("/directeur/update-medecin/{id}")
    public ResponseEntity<?> updateMedecin(@PathVariable Long id, @RequestBody MedecinRequest medecinRequest) {
        try {
            MedecinResponse updatedMedecin = medecinService.UpdateMedecin(id, medecinRequest);
            return ResponseEntity.ok(updatedMedecin);
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne : " + ex.getMessage());
        }
    }


    @PutMapping("/medecin/update-medecin/{id}")
    public ResponseEntity<?> updateMedecinParLuiMeme(@PathVariable Long id, @RequestBody MedecinRequest medecinRequest) {
        try {
            User currentUser = currentUserUtil.getCurrentUser();

            Medecin medecin = medecinService.getMedecinEntityById(id);

            if (!currentUser.getId().equals(medecin.getUser().getId())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Vous n'êtes pas autorisé à modifier cet utilisateur.");
            }
            MedecinResponse updatedMedecin = medecinService.UpdateMedecin(id, medecinRequest);
            return ResponseEntity.ok(updatedMedecin);
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne : " + ex.getMessage());
        }
    }

}
