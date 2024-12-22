package org.example.uml_hospital.Services.ServicesImp;

import org.example.uml_hospital.Dtos.SecretaireRequest;
import org.example.uml_hospital.Entities.Role;
import org.example.uml_hospital.Entities.Secretaire;
import org.example.uml_hospital.Entities.User;
import org.example.uml_hospital.Repositories.SecretaireRepository;
import org.example.uml_hospital.Repositories.UserRepository;
import org.example.uml_hospital.Services.ServicesInterfaces.SecretaireService;
import org.example.uml_hospital.Utils.EmailSender;
import org.example.uml_hospital.Utils.PasswordGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class SecretaireServiceImp implements SecretaireService {
    private final UserRepository userRepository;
    private final SecretaireRepository secretaireRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailSender emailSender;

    public SecretaireServiceImp(UserRepository userRepository, SecretaireRepository secretaireRepository, PasswordEncoder passwordEncoder,EmailSender emailSender) {
        this.userRepository = userRepository;
        this.secretaireRepository = secretaireRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailSender=emailSender;
    }

    @Override
    public Secretaire creerSecretaire(SecretaireRequest request) {
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
                "votre compte au tant que secretaire a été crée",
                "voici votre mot de passe que vous pouvez le changer" + motDePasseAleatoire);

        // Créer le médecin
        Secretaire secretaire = new Secretaire();
        secretaire.setUser(user);

        // Sauvegarder dans la base de données
        userRepository.save(user);
        secretaireRepository.save(secretaire);

        // Retourner le médecin
        return secretaire;    }
}
