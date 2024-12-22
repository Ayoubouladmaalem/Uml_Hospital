package org.example.uml_hospital.Services.ServicesImp;


import org.example.uml_hospital.Dtos.PharmacienRequest;
import org.example.uml_hospital.Dtos.SecretaireRequest;
import org.example.uml_hospital.Entities.Pharmacien;
import org.example.uml_hospital.Entities.Role;
import org.example.uml_hospital.Entities.Secretaire;
import org.example.uml_hospital.Entities.User;
import org.example.uml_hospital.Repositories.PharmacienRepository;
import org.example.uml_hospital.Repositories.SecretaireRepository;
import org.example.uml_hospital.Repositories.UserRepository;
import org.example.uml_hospital.Services.ServicesInterfaces.PharmacienService;
import org.example.uml_hospital.Utils.EmailSender;
import org.example.uml_hospital.Utils.PasswordGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PharmacienServiceImp implements PharmacienService {
    private final UserRepository userRepository;
    private final PharmacienRepository pharmacienRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailSender emailSender;

    public PharmacienServiceImp(UserRepository userRepository, PharmacienRepository pharmacienRepository, PasswordEncoder passwordEncoder,EmailSender emailSender) {
        this.userRepository = userRepository;
        this.pharmacienRepository = pharmacienRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailSender=emailSender;
    }

    @Override
    public Pharmacien creerPharmacien(PharmacienRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email déjà utilisé : " + request.getEmail());
        }
        // Générer un mot de passe aléatoire
        String motDePasseAleatoire = PasswordGenerator.generatePassword();

        // Créer l'utilisateur associé
        User user = new User();
        user.setNom(request.getNom());
        user.setPrenom(request.getPrenom());
        user.setSexe(request.getSexe());
        user.setDateNaissance(request.getDateNaissance());
        user.setTelephone(request.getTelephone());
        user.setEmail(request.getEmail());
        user.setMotDePasse(passwordEncoder.encode(motDePasseAleatoire));
        user.setRole(Role.medecin);
        emailSender.sendSimpleEmail(user.getEmail(),
                "votre compte au tant que pharmacien a été crée",
                "voici votre mot de passe que vous pouvez le changer" + motDePasseAleatoire);

        // Créer le médecin
        Pharmacien pharmacien = new Pharmacien();
        pharmacien.setUser(user);

        // Sauvegarder dans la base de données
        userRepository.save(user);
        pharmacienRepository.save(pharmacien);

        // Retourner le médecin
        return pharmacien;    }
}
