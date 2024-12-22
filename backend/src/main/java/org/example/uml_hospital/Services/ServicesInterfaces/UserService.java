package org.example.uml_hospital.Services.ServicesInterfaces;

import org.example.uml_hospital.Dtos.LoginRequest;
import org.example.uml_hospital.Dtos.RegisterRequest;
import org.example.uml_hospital.Entities.User;

public interface UserService {
    User register(RegisterRequest request);
    User authenticate(LoginRequest request);
    User getUserFromToken(String token);
}
