package org.example.uml_hospital.Services.ServicesImp;

import org.example.uml_hospital.Config.JwtUtil;
import org.example.uml_hospital.Dtos.LoginRequest;
import org.example.uml_hospital.Dtos.RegisterRequest;
import org.example.uml_hospital.Entities.Role;
import org.example.uml_hospital.Entities.User;
import org.example.uml_hospital.Repositories.UserRepository;
import org.example.uml_hospital.Services.ServicesInterfaces.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    public UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil=jwtUtil;
    }

    @Override
    public User register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email déjà utilisé : " + request.getEmail());
        }

        User user = new User();
        user.setNom(request.getNom());
        user.setPrenom(request.getPrenom());
        user.setSexe(request.getSexe());
        user.setDateNaissance(request.getDateNaissance());
        user.setTelephone(request.getTelephone());
        user.setEmail(request.getEmail());
        user.setMotDePasse(passwordEncoder.encode(request.getMotDePasse()));
        user.setRole(Role.patient);

        return userRepository.save(user);
    }

    @Override
    public User authenticate(LoginRequest request) {
        // Charger l'utilisateur par email
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Email non trouvé"));

        // Vérifier le mot de passe
        if (!passwordEncoder.matches(request.getMotDePasse(), user.getMotDePasse())) {
            throw new RuntimeException("Mot de passe incorrect");
        }

        return user; // Retourne l'utilisateur authentifié
    }
    @Override
    public User getUserFromToken(String token) {
        String email = jwtUtil.extractEmail(token); // Extraire l'email depuis le JWT
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'email : " + email));
    }


}
