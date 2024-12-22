package org.example.uml_hospital.Controllers;

import org.example.uml_hospital.Dtos.MedecinRequest;

import org.example.uml_hospital.Dtos.PharmacienRequest;
import org.example.uml_hospital.Dtos.SecretaireRequest;
import org.example.uml_hospital.Services.ServicesInterfaces.MedecinService;
import org.example.uml_hospital.Services.ServicesInterfaces.PharmacienService;
import org.example.uml_hospital.Services.ServicesInterfaces.SecretaireService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/directeur")
@CrossOrigin(origins = "http://localhost:3000")
public class DirecteurController {

    private final MedecinService medecinService;
    private final SecretaireService secretaireService;
    private final PharmacienService pharmacienService;

    public DirecteurController(MedecinService medecinService , SecretaireService secretaireService ,PharmacienService pharmacienService) {
        this.medecinService = medecinService;
        this.secretaireService=secretaireService;
        this.pharmacienService=pharmacienService;
    }

    @PostMapping("/creer-medecin")
    public ResponseEntity<String> creerMedecin(@RequestBody MedecinRequest request) {
        medecinService.creerMedecin(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Médecin créé avec succès !");
    }

    @PostMapping("/creer-secretaire")
    public ResponseEntity<String> creerSecretaire(@RequestBody SecretaireRequest request) {
        secretaireService.creerSecretaire(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Secretaire créé avec succès !");
    }

    @PostMapping("/creer-pharmacien")
    public ResponseEntity<String> creerPharmacien(@RequestBody PharmacienRequest request) {
        pharmacienService.creerPharmacien(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Pharmacien créé avec succès !");
    }

}

