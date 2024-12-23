package org.example.uml_hospital.Services.ServicesImp;

import org.example.uml_hospital.Dtos.Mapper;
import org.example.uml_hospital.Dtos.Request.SecretaireRequest;
import org.example.uml_hospital.Dtos.Response.SecretaireResponse;
import org.example.uml_hospital.Entities.Pharmacien;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class SecretaireServiceImp implements SecretaireService {
    private final UserRepository userRepository;
    private final SecretaireRepository secretaireRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailSender emailSender;
    private final Mapper mapper;

    public SecretaireServiceImp(UserRepository userRepository, SecretaireRepository secretaireRepository, PasswordEncoder passwordEncoder,EmailSender emailSender , Mapper mapper) {
        this.userRepository = userRepository;
        this.secretaireRepository = secretaireRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailSender=emailSender;
        this.mapper=mapper;
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
        user.setRole(Role.secretaire);
        emailSender.sendSimpleEmail(user.getEmail(),
                "votre compte au tant que secretaire a été crée",
                "voici votre mot de passe que vous pouvez le changer" + motDePasseAleatoire);

        Secretaire secretaire = new Secretaire();
        secretaire.setUser(user);

        userRepository.save(user);
        secretaireRepository.save(secretaire);

        return secretaire;
    }


    @Override
    public void deleteSecretaire(long id) {
        if (!secretaireRepository.existsById(id)){
            throw new IllegalStateException("Category with this id " + id + "is not found");
        }
        secretaireRepository.deleteById(id);
    }

    @Override
    public List<SecretaireResponse> getAllSecretaires() {
        return secretaireRepository.findAll()
                .stream()
                .map(mapper::convertToSecretaireResponse)
                .collect(Collectors.toList());
    }

    @Override
    public SecretaireResponse getSecretaire(Long id) {
        Secretaire secretaire=secretaireRepository.findById(id)
                .orElseThrow(()->new IllegalStateException("Secretaire avec cet id" + id + "n'existe pas"));
        return mapper.convertToSecretaireResponse(secretaire);
    }

    @Override
    public Secretaire getSecretaireEntityById(Long id) {
        return secretaireRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Secretaire avec cet ID " + id + " n'existe pas."));
    }

    @Override
    public SecretaireResponse updateSecretaire(Long id, SecretaireRequest secretaireRequest) {
        Secretaire secretaire = secretaireRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Secrétaire avec cet ID " + id + " n'existe pas."));

        if (secretaireRequest.getEmail() != null && !secretaireRequest.getEmail().isEmpty()) {
            Optional<User> existingUser = userRepository.findByEmail(secretaireRequest.getEmail());
            if (existingUser.isPresent() && !existingUser.get().getId().equals(secretaire.getUser().getId())) {
                throw new IllegalStateException("L'email " + secretaireRequest.getEmail() + " est déjà utilisé.");
            }
        }

        if (secretaireRequest.getNom() != null && !secretaireRequest.getNom().isEmpty()) {
            secretaire.getUser().setNom(secretaireRequest.getNom());
        }
        if (secretaireRequest.getPrenom() != null && !secretaireRequest.getPrenom().isEmpty()) {
            secretaire.getUser().setPrenom(secretaireRequest.getPrenom());
        }
        if (secretaireRequest.getSexe() != null && !secretaireRequest.getSexe().isEmpty()) {
            secretaire.getUser().setSexe(secretaireRequest.getSexe());
        }
        if (secretaireRequest.getDateNaissance() != null) {
            secretaire.getUser().setDateNaissance(secretaireRequest.getDateNaissance());
        }
        if (secretaireRequest.getTelephone() != null && !secretaireRequest.getTelephone().isEmpty()) {
            secretaire.getUser().setTelephone(secretaireRequest.getTelephone());
        }

        secretaireRepository.save(secretaire);

        return mapper.convertToSecretaireResponse(secretaire);
    }

}
