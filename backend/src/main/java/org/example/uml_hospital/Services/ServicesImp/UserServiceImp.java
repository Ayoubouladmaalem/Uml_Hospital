package org.example.uml_hospital.Services.ServicesImp;

import org.example.uml_hospital.Config.JwtUtil;
import org.example.uml_hospital.Dtos.Request.LoginRequest;
import org.example.uml_hospital.Dtos.Request.RegisterRequest;
import org.example.uml_hospital.Entities.Patient;
import org.example.uml_hospital.Entities.Role;
import org.example.uml_hospital.Entities.User;
import org.example.uml_hospital.Repositories.PatientRepository;
import org.example.uml_hospital.Repositories.UserRepository;
import org.example.uml_hospital.Services.ServicesInterfaces.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final PatientRepository patientRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    public UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil , PatientRepository patientRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil=jwtUtil;
        this.patientRepository=patientRepository;
    }

    @Override
    public User register(RegisterRequest request) {
        if (request.getNom() == null || request.getNom().isEmpty()) {
            throw new IllegalArgumentException("Le nom est obligatoire.");
        }
        if (request.getPrenom() == null || request.getPrenom().isEmpty()) {
            throw new IllegalArgumentException("Le prénom est obligatoire.");
        }
        if (request.getSexe() == null || request.getSexe().isEmpty()) {
            throw new IllegalArgumentException("Le sexe est obligatoire.");
        }
        if (request.getDateNaissance() == null) {
            throw new IllegalArgumentException("La date de naissance est obligatoire.");
        }
        if (request.getTelephone() == null || request.getTelephone().isEmpty()) {
            throw new IllegalArgumentException("Le numéro de téléphone est obligatoire.");
        }
        if (request.getEmail() == null || request.getEmail().isEmpty()) {
            throw new IllegalArgumentException("L'email est obligatoire.");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email déjà utilisé : " + request.getEmail());
        }
        if (request.getMotDePasse() == null || request.getMotDePasse().isEmpty()) {
            throw new IllegalArgumentException("Le mot de passe est obligatoire.");
        }
        if (!request.getMotDePasse().equals(request.getConfirmationMotDePasse())) {
            throw new IllegalArgumentException("Le mot de passe et la confirmation ne correspondent pas.");
        }
        if (request.getPoids() <= 0) {
            throw new IllegalArgumentException("Le poids doit être supérieur à 0.");
        }

        // Création de l'utilisateur
        User user = new User();
        user.setNom(request.getNom());
        user.setPrenom(request.getPrenom());
        user.setSexe(request.getSexe());
        user.setDateNaissance(request.getDateNaissance());
        user.setTelephone(request.getTelephone());
        user.setEmail(request.getEmail());
        user.setMotDePasse(passwordEncoder.encode(request.getMotDePasse()));
        user.setRole(Role.patient);

        Patient patient = new Patient();
        patient.setPoids(request.getPoids());
        patient.setUser(user);

        userRepository.save(user);
        patientRepository.save(patient);

        return userRepository.save(user);
    }





    @Override
    public User authenticate(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Email non trouvé"));

        if (!passwordEncoder.matches(request.getMotDePasse(), user.getMotDePasse())) {
            throw new RuntimeException("Mot de passe incorrect");
        }

        return user;
    }
    @Override
    public User getUserFromToken(String token) {
        String email = jwtUtil.extractEmail(token);
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'email : " + email));
    }


}
