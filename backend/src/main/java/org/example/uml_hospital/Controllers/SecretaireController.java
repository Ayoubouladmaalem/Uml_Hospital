package org.example.uml_hospital.Controllers;

import org.example.uml_hospital.Dtos.Request.SecretaireRequest;
import org.example.uml_hospital.Dtos.Response.SecretaireResponse;
import org.example.uml_hospital.Entities.Secretaire;
import org.example.uml_hospital.Entities.User;
import org.example.uml_hospital.Services.ServicesInterfaces.SecretaireService;
import org.example.uml_hospital.Utils.CurrentUserUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class SecretaireController {

    private final SecretaireService secretaireService;
    private final CurrentUserUtil currentUserUtil;

    public SecretaireController(SecretaireService secretaireService, CurrentUserUtil currentUserUtil) {
        this.secretaireService = secretaireService;
        this.currentUserUtil = currentUserUtil;
    }

    @PostMapping("/directeur/creer-secretaire")
    public ResponseEntity<?> creerSecretaire(@RequestBody SecretaireRequest request) {
        try {
            secretaireService.creerSecretaire(request);
            return ResponseEntity.status(HttpStatus.CREATED).body("Secrétaire créé avec succès !");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne : " + ex.getMessage());
        }
    }

    @DeleteMapping("/directeur/supprimer-secretaire/{id}")
    public ResponseEntity<?> supprimerSecretaire(@PathVariable Long id) {
        try {
            secretaireService.deleteSecretaire(id);
            return ResponseEntity.ok("Secrétaire supprimé avec succès !");
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne : " + ex.getMessage());
        }
    }

    @GetMapping("/directeur/secretaires")
    public ResponseEntity<?> getSecretaires() {
        try {
            List<SecretaireResponse> secretaires = secretaireService.getAllSecretaires();
            return ResponseEntity.ok(secretaires);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne : " + ex.getMessage());
        }
    }

    @GetMapping("/secretaire/{id}")
    public ResponseEntity<?> getSecretaire(@PathVariable Long id) {
        try {
            User currentUser = currentUserUtil.getCurrentUser();

            Secretaire secretaire = secretaireService.getSecretaireEntityById(id);

            if (!currentUser.getId().equals(secretaire.getUser().getId())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Vous n'êtes pas autorisé à voir cet utilisateur.");
            }

            SecretaireResponse secretaireResponse = secretaireService.getSecretaire(id);
            return ResponseEntity.ok(secretaireResponse);
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne : " + ex.getMessage());
        }
    }

    @PutMapping("/directeur/update-secretaire/{id}")
    public ResponseEntity<?> updateSecretaire(@PathVariable Long id, @RequestBody SecretaireRequest secretaireRequest) {
        try {
            SecretaireResponse updatedSecretaire = secretaireService.updateSecretaire(id, secretaireRequest);
            return ResponseEntity.ok(updatedSecretaire);
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne : " + ex.getMessage());
        }
    }

    @PutMapping("/secretaire/update-secretaire/{id}")
    public ResponseEntity<?> updateSecretaireParLuiMeme(@PathVariable Long id, @RequestBody SecretaireRequest secretaireRequest) {
        try {
            User currentUser = currentUserUtil.getCurrentUser();

            Secretaire secretaire = secretaireService.getSecretaireEntityById(id);

            if (!currentUser.getId().equals(secretaire.getUser().getId())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Vous n'êtes pas autorisé à modifier cet utilisateur.");
            }

            SecretaireResponse updatedSecretaire = secretaireService.updateSecretaire(id, secretaireRequest);
            return ResponseEntity.ok(updatedSecretaire);
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne : " + ex.getMessage());
        }
    }
}
