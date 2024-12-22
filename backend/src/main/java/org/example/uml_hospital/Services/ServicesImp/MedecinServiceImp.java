package org.example.uml_hospital.Services.ServicesImp;

import org.example.uml_hospital.Dtos.Mapper;
import org.example.uml_hospital.Dtos.Request.MedecinRequest;
import org.example.uml_hospital.Dtos.Response.MedecinResponse;
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


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class MedecinServiceImp implements MedecinService {
    private final UserRepository userRepository;
    private final MedecinRepository medecinRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailSender emailSender;
    private final Mapper mapper;

    public MedecinServiceImp(UserRepository userRepository, MedecinRepository medecinRepository, PasswordEncoder passwordEncoder,EmailSender emailSender,Mapper mapper) {
        this.userRepository = userRepository;
        this.medecinRepository = medecinRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailSender=emailSender;
        this.mapper=mapper;
    }

    public Medecin creerMedecin(MedecinRequest request) {
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
        user.setRole(Role.medecin);
        emailSender.sendSimpleEmail(user.getEmail(),
                "votre compte au tant que médecin a été crée",
                "voici votre mot de passe que vous pouvez le changer" + motDePasseAleatoire);

        Medecin medecin = new Medecin();
        medecin.setSpecialite(request.getSpecialite());
        medecin.setUser(user);

        userRepository.save(user);
        medecinRepository.save(medecin);

        return medecin;
    }

    @Override
    public void deleteMedecin(long id) {
        if (!medecinRepository.existsById(id)){
            throw new IllegalStateException("medecin avec cet id" + id + "n'existe pas");
        }
        medecinRepository.deleteById(id);
    }

    @Override
    public List<MedecinResponse> getAllMedecins() {
        return medecinRepository.findAll()
                .stream()
                .map(mapper::convertToMedecinResponse)
                .collect(Collectors.toList());
    }

    @Override
    public MedecinResponse getMedecin(Long id) {
        Medecin medecin=medecinRepository.findById(id)
                .orElseThrow(()->new IllegalStateException("medecin avec cet id" + id + "n'existe pas"));
        return mapper.convertToMedecinResponse(medecin);
    }

    @Override
    public Medecin getMedecinEntityById(Long id) {
        return medecinRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Médecin avec cet ID " + id + " n'existe pas."));
    }


    @Override
    public MedecinResponse UpdateMedecin(Long id, MedecinRequest medecinRequest) {
        Medecin medecin = medecinRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Médecin avec cet ID " + id + " n'existe pas."));
        if (medecinRequest.getEmail() != null && !medecinRequest.getEmail().isEmpty()) {
            Optional<User> existingUser = userRepository.findByEmail(medecinRequest.getEmail());
            if (existingUser.isPresent() && !existingUser.get().getId().equals(medecin.getUser().getId())) {
                throw new IllegalStateException("L'email " + medecinRequest.getEmail() + " est déjà utilisé.");
            }
        }
        if (medecinRequest.getNom() != null && !medecinRequest.getNom().isEmpty()) {
            medecin.getUser().setNom(medecinRequest.getNom());
        }
        if (medecinRequest.getPrenom() != null && !medecinRequest.getPrenom().isEmpty()) {
            medecin.getUser().setPrenom(medecinRequest.getPrenom());
        }
        if (medecinRequest.getSexe() != null && !medecinRequest.getSexe().isEmpty()) {
            medecin.getUser().setSexe(medecinRequest.getSexe());
        }
        if (medecinRequest.getDateNaissance() != null) {
            medecin.getUser().setDateNaissance(medecinRequest.getDateNaissance());
        }
        if (medecinRequest.getTelephone() != null && !medecinRequest.getTelephone().isEmpty()) {
            medecin.getUser().setTelephone(medecinRequest.getTelephone());
        }
        if (medecinRequest.getSpecialite() != null && !medecinRequest.getSpecialite().isEmpty()) {
            medecin.setSpecialite(medecinRequest.getSpecialite());
        }
        medecinRepository.save(medecin);
        return mapper.convertToMedecinResponse(medecin);
    }

}
