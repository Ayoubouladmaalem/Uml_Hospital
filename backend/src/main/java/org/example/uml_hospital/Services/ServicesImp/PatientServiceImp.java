package org.example.uml_hospital.Services.ServicesImp;

import org.example.uml_hospital.Dtos.Mapper;
import org.example.uml_hospital.Dtos.Request.PatientRequest;
import org.example.uml_hospital.Dtos.Response.PatientResponse;
import org.example.uml_hospital.Entities.Patient;
import org.example.uml_hospital.Entities.User;
import org.example.uml_hospital.Repositories.PatientRepository;
import org.example.uml_hospital.Repositories.UserRepository;
import org.example.uml_hospital.Services.ServicesInterfaces.PatientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientServiceImp implements PatientService {


    private final UserRepository userRepository;
    private final PatientRepository patientRepository;

    private final Mapper mapper;



    public PatientServiceImp(UserRepository userRepository, PatientRepository patientRepository, Mapper mapper) {
        this.userRepository = userRepository;
        this.patientRepository = patientRepository;

        this.mapper=mapper;
    }
    @Override
    public void deletePatient(long id) {
        if (!patientRepository.existsById(id)){
            throw new IllegalStateException("patient avec cet id" + id + "n'existe pas");
        }
        patientRepository.deleteById(id);

    }

    @Override
    public List<PatientResponse> getAllPatients() {
        return patientRepository.findAll()
                .stream()
                .map(mapper::convertToPatientResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PatientResponse getPatient(Long id) {
        Patient patient=patientRepository.findById(id)
                .orElseThrow(()->new IllegalStateException("patient avec cet id" + id + "n'existe pas"));
        return mapper.convertToPatientResponse(patient);
    }

    @Override
    public PatientResponse UpdatePatient(Long id, PatientRequest patientRequest) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Médecin avec cet ID " + id + " n'existe pas."));
        if (patientRequest.getEmail() != null && !patientRequest.getEmail().isEmpty()) {
            Optional<User> existingUser = userRepository.findByEmail(patientRequest.getEmail());
            if (existingUser.isPresent() && !existingUser.get().getId().equals(patient.getUser().getId())) {
                throw new IllegalStateException("L'email " + patientRequest.getEmail() + " est déjà utilisé.");
            }
        }
        if (patientRequest.getNom() != null && !patientRequest.getNom().isEmpty()) {
            patient.getUser().setNom(patientRequest.getNom());
        }
        if (patientRequest.getPrenom() != null && !patientRequest.getPrenom().isEmpty()) {
            patient.getUser().setPrenom(patientRequest.getPrenom());
        }
        if (patientRequest.getSexe() != null && !patientRequest.getSexe().isEmpty()) {
            patient.getUser().setSexe(patientRequest.getSexe());
        }
        if (patientRequest.getDateNaissance() != null) {
            patient.getUser().setDateNaissance(patientRequest.getDateNaissance());
        }
        if (patientRequest.getTelephone() != null && !patientRequest.getTelephone().isEmpty()) {
            patient.getUser().setTelephone(patientRequest.getTelephone());
        }
        if (patientRequest.getPoids() != 0.0f) {
            patient.setPoids(patientRequest.getPoids());
        }
        patientRepository.save(patient);
        return mapper.convertToPatientResponse(patient);
    }

    @Override
    public Patient getPatientEntityById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Patient avec cet ID " + id + " n'existe pas."));
    }
}
