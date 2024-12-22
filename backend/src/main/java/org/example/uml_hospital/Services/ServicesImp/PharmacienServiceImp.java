package org.example.uml_hospital.Services.ServicesImp;


import org.example.uml_hospital.Dtos.Mapper;
import org.example.uml_hospital.Dtos.Request.PharmacienRequest;
import org.example.uml_hospital.Dtos.Response.PharmacienResponse;
import org.example.uml_hospital.Entities.Medecin;
import org.example.uml_hospital.Entities.Pharmacien;
import org.example.uml_hospital.Entities.Role;
import org.example.uml_hospital.Entities.User;
import org.example.uml_hospital.Repositories.PharmacienRepository;
import org.example.uml_hospital.Repositories.UserRepository;
import org.example.uml_hospital.Services.ServicesInterfaces.PharmacienService;
import org.example.uml_hospital.Utils.EmailSender;
import org.example.uml_hospital.Utils.PasswordGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PharmacienServiceImp implements PharmacienService {
    private final UserRepository userRepository;
    private final PharmacienRepository pharmacienRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailSender emailSender;
    private final Mapper mapper;


    public PharmacienServiceImp(UserRepository userRepository, PharmacienRepository pharmacienRepository, PasswordEncoder passwordEncoder,EmailSender emailSender ,Mapper mapper) {
        this.userRepository = userRepository;
        this.pharmacienRepository = pharmacienRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailSender=emailSender;
        this.mapper=mapper;

    }

    @Override
    public Pharmacien creerPharmacien(PharmacienRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email déjà utilisé : " + request.getEmail());
        }
        String motDePasseAleatoire = PasswordGenerator.generatePassword();

        User user = new User();
        user.setNom(request.getNom());
        user.setPrenom(request.getPrenom());
        user.setSexe(request.getSexe());
        user.setDateNaissance(request.getDateNaissance());
        user.setTelephone(request.getTelephone());
        user.setEmail(request.getEmail());
        user.setMotDePasse(passwordEncoder.encode(motDePasseAleatoire));
        user.setRole(Role.pharmacien);
        emailSender.sendSimpleEmail(user.getEmail(),
                "votre compte au tant que pharmacien a été crée",
                "voici votre mot de passe que vous pouvez le changer" + motDePasseAleatoire);

        Pharmacien pharmacien = new Pharmacien();
        pharmacien.setUser(user);

        userRepository.save(user);
        pharmacienRepository.save(pharmacien);

        return pharmacien;
    }

    @Override
    public void deletePharmacien(long id) {
        if (!pharmacienRepository.existsById(id)){
            throw new IllegalStateException("Category with this id " + id + "is not found");
        }
        pharmacienRepository.deleteById(id);
    }

    @Override
    public List<PharmacienResponse> getAllPharmaciens() {
        return pharmacienRepository.findAll()
                .stream()
                .map(mapper::convertToPharmacienResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PharmacienResponse getPharmacien(Long id) {
        Pharmacien pharmacien=pharmacienRepository.findById(id)
                .orElseThrow(()->new IllegalStateException("pharmacien avec cet id" + id + "n'existe pas"));
        return mapper.convertToPharmacienResponse(pharmacien);
    }

    @Override
    public Pharmacien getPharmacienEntityById(Long id) {
        return pharmacienRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Pharmacien avec cet ID " + id + " n'existe pas."));
    }

    @Override
    public PharmacienResponse updatePharmacien(Long id, PharmacienRequest pharmacienRequest) {

        Pharmacien pharmacien = pharmacienRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Pharmacien avec cet ID " + id + " n'existe pas."));


        if (pharmacienRequest.getEmail() != null && !pharmacienRequest.getEmail().isEmpty()) {
            Optional<User> existingUser = userRepository.findByEmail(pharmacienRequest.getEmail());
            if (existingUser.isPresent() && !existingUser.get().getId().equals(pharmacien.getUser().getId())) {
                throw new IllegalStateException("L'email " + pharmacienRequest.getEmail() + " est déjà utilisé.");
            }
        }

        if (pharmacienRequest.getNom() != null && !pharmacienRequest.getNom().isEmpty()) {
            pharmacien.getUser().setNom(pharmacienRequest.getNom());
        }
        if (pharmacienRequest.getPrenom() != null && !pharmacienRequest.getPrenom().isEmpty()) {
            pharmacien.getUser().setPrenom(pharmacienRequest.getPrenom());
        }
        if (pharmacienRequest.getSexe() != null && !pharmacienRequest.getSexe().isEmpty()) {
            pharmacien.getUser().setSexe(pharmacienRequest.getSexe());
        }
        if (pharmacienRequest.getDateNaissance() != null) {
            pharmacien.getUser().setDateNaissance(pharmacienRequest.getDateNaissance());
        }
        if (pharmacienRequest.getTelephone() != null && !pharmacienRequest.getTelephone().isEmpty()) {
            pharmacien.getUser().setTelephone(pharmacienRequest.getTelephone());
        }
        if (pharmacienRequest.getEmail() != null && !pharmacienRequest.getEmail().isEmpty()) {
            pharmacien.getUser().setEmail(pharmacienRequest.getEmail());
        }

        pharmacienRepository.save(pharmacien);
        return mapper.convertToPharmacienResponse(pharmacien);
    }

}
