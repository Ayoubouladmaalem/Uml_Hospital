package org.example.uml_hospital.Services.ServicesImp;

import org.example.uml_hospital.Dtos.MedecinRequest;
import org.example.uml_hospital.Entities.Medecin;
import org.example.uml_hospital.Entities.Role;
import org.example.uml_hospital.Entities.User;
import org.example.uml_hospital.Repositories.MedecinRepository;
import org.example.uml_hospital.Repositories.UserRepository;
import org.example.uml_hospital.Services.ServicesInterfaces.MedecinService;
import org.example.uml_hospital.Utils.EmailSender;
import org.example.uml_hospital.Utils.PasswordGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class MedecinServiceImp implements MedecinService {
    private final UserRepository userRepository;
    private final MedecinRepository medecinRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailSender emailSender;

    public MedecinServiceImp(UserRepository userRepository, MedecinRepository medecinRepository, PasswordEncoder passwordEncoder,EmailSender emailSender) {
        this.userRepository = userRepository;
        this.medecinRepository = medecinRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailSender=emailSender;
    }

    public Medecin creerMedecin(MedecinRequest request) {
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
                "votre compte au tant que médecin a été crée",
                "voici votre mot de passe que vous pouvez le changer" + motDePasseAleatoire);

        // Créer le médecin
        Medecin medecin = new Medecin();
        medecin.setSpecialite(request.getSpecialite());
        medecin.setUser(user);

        // Sauvegarder dans la base de données
        userRepository.save(user);
        medecinRepository.save(medecin);

        // Retourner le médecin
        return medecin;
    }
}
