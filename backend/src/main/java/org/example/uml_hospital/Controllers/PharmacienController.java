package org.example.uml_hospital.Controllers;

import org.example.uml_hospital.Dtos.Request.PharmacienRequest;
import org.example.uml_hospital.Dtos.Response.PharmacienResponse;
import org.example.uml_hospital.Entities.Pharmacien;
import org.example.uml_hospital.Entities.User;
import org.example.uml_hospital.Services.ServicesInterfaces.PharmacienService;
import org.example.uml_hospital.Utils.CurrentUserUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PharmacienController {

    private final PharmacienService pharmacienService;
    private final CurrentUserUtil currentUserUtil;

    public PharmacienController(PharmacienService pharmacienService, CurrentUserUtil currentUserUtil) {
        this.pharmacienService = pharmacienService;
        this.currentUserUtil = currentUserUtil;
    }

    @PostMapping("/directeur/creer-pharmacien")
    public ResponseEntity<?> creerPharmacien(@RequestBody PharmacienRequest request) {
        try {
            pharmacienService.creerPharmacien(request);
            return ResponseEntity.status(HttpStatus.CREATED).body("Pharmacien créé avec succès !");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne : " + ex.getMessage());
        }
    }

    @DeleteMapping("/directeur/supprimer-pharmacien/{id}")
    public ResponseEntity<?> supprimerPharmacien(@PathVariable Long id) {
        try {
            pharmacienService.deletePharmacien(id);
            return ResponseEntity.ok("Pharmacien supprimé avec succès !");
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne : " + ex.getMessage());
        }
    }

    @GetMapping("/directeur/pharmaciens")
    public ResponseEntity<List<PharmacienResponse>> getPharmaciens() {
        try {
            List<PharmacienResponse> pharmaciens = pharmacienService.getAllPharmaciens();
            return ResponseEntity.ok(pharmaciens);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/pharmacien/{id}")
    public ResponseEntity<?> getPharmacien(@PathVariable Long id) {
        try {
            User currentUser = currentUserUtil.getCurrentUser();

            Pharmacien pharmacien = pharmacienService.getPharmacienEntityById(id);

            if (!currentUser.getId().equals(pharmacien.getUser().getId())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Vous n'êtes pas autorisé à voir cet utilisateur.");
            }
            PharmacienResponse pharmacienResponse = pharmacienService.getPharmacien(id);
            return ResponseEntity.ok(pharmacienResponse);
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne : " + ex.getMessage());
        }
    }

    @PutMapping("/directeur/update-pharmacien/{id}")
    public ResponseEntity<?> updatePharmacien(@PathVariable Long id, @RequestBody PharmacienRequest pharmacienRequest) {
        try {
            PharmacienResponse updatedPharmacien = pharmacienService.updatePharmacien(id, pharmacienRequest);
            return ResponseEntity.ok(updatedPharmacien);
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne : " + ex.getMessage());
        }
    }

    @PutMapping("/pharmacien/update-pharmacien/{id}")
    public ResponseEntity<?> updatePharmacienParLuiMeme(@PathVariable Long id, @RequestBody PharmacienRequest pharmacienRequest) {
        try {
            User currentUser = currentUserUtil.getCurrentUser();

            Pharmacien pharmacien = pharmacienService.getPharmacienEntityById(id);

            if (!currentUser.getId().equals(pharmacien.getUser().getId())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Vous n'êtes pas autorisé à modifier cet utilisateur.");
            }
            PharmacienResponse updatedPharmacien = pharmacienService.updatePharmacien(id, pharmacienRequest);
            return ResponseEntity.ok(updatedPharmacien);
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne : " + ex.getMessage());
        }
    }
}
