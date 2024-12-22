package org.example.uml_hospital.Controllers;

import org.example.uml_hospital.Dtos.LoginRequest;
import org.example.uml_hospital.Dtos.RegisterRequest;
import org.example.uml_hospital.Dtos.UserResponse;
import org.example.uml_hospital.Entities.User;
import org.example.uml_hospital.Repositories.UserRepository;
import org.example.uml_hospital.Services.ServicesInterfaces.UserService;
import org.example.uml_hospital.Config.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public AuthController(UserService userService, JwtUtil jwtUtil , UserRepository userRepository) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.userRepository=userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Validated @RequestBody RegisterRequest request) {
        User user = userService.register(request);
        return ResponseEntity.ok("Utilisateur enregistré avec succès : " + user.getEmail());
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        User user = userService.authenticate(request);
        String jwt = jwtUtil.generateToken(user.getEmail() , user.getRole().name());
        return ResponseEntity.ok(jwt);
    }

    @GetMapping("/current-user")
    public ResponseEntity<UserResponse> getCurrentUser() {
        // Récupérer l'utilisateur connecté depuis le contexte de sécurité
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        // Retourner une réponse personnalisée
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setNom(user.getNom());
        response.setPrenom(user.getPrenom());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole().name());

        return ResponseEntity.ok(response);
    }

}
