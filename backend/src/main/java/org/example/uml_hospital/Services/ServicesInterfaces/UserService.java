package org.example.uml_hospital.Services.ServicesInterfaces;

import org.example.uml_hospital.Dtos.Request.LoginRequest;
import org.example.uml_hospital.Dtos.Request.RegisterRequest;
import org.example.uml_hospital.Entities.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User register(RegisterRequest request);
    User authenticate(LoginRequest request);
    User getUserFromToken(String token);
}
