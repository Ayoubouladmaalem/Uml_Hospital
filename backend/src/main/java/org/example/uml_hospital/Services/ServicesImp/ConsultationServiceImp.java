package org.example.uml_hospital.Services.ServicesImp;

import org.example.uml_hospital.Dtos.Mapper;
import org.example.uml_hospital.Dtos.Request.ConsultationRequest;
import org.example.uml_hospital.Dtos.Response.ConsultationResponse;
import org.example.uml_hospital.Entities.*;
import org.example.uml_hospital.Repositories.ConsultationRepository;
import org.example.uml_hospital.Repositories.MedecinRepository;
import org.example.uml_hospital.Repositories.PatientRepository;
import org.example.uml_hospital.Repositories.UserRepository;
import org.example.uml_hospital.Services.ServicesInterfaces.ConsultationService;
import org.example.uml_hospital.Utils.CurrentUserUtil;
import org.example.uml_hospital.Utils.EmailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ConsultationServiceImp implements ConsultationService {

    private final PatientRepository patientRepository;
    private final MedecinRepository medecinRepository;
    private final ConsultationRepository consultationRepository;
    private final EmailSender emailSender;
    private final Mapper mapper;
    private final CurrentUserUtil currentUserUtil;


    public ConsultationServiceImp(PatientRepository patientRepository, MedecinRepository medecinRepository, ConsultationRepository consultationRepository,EmailSender emailSender,Mapper mapper,CurrentUserUtil currentUserUtil) {
        this.patientRepository = patientRepository;
        this.medecinRepository = medecinRepository;
        this.consultationRepository = consultationRepository;
        this.emailSender=emailSender;
        this.mapper=mapper;
        this.currentUserUtil=currentUserUtil;
    }


    public Medecin attachMedecinToConsultation(String specialite, Date date_consultation) {
        List<Medecin> medecins = medecinRepository.getMedecinsBySpecialite(specialite);
        if (medecins.isEmpty()) {
            throw new RuntimeException("Aucun médecin trouvé pour la spécialité : " + specialite);
        }
        Medecin medecinChoisi = null;
        int minConsultations = 10000;
        for (Medecin medecin : medecins) {
            int consultationsCount = consultationRepository.countByMedecinAndDateConsultation(medecin, date_consultation);
            if (consultationsCount == 0) {
                return medecin;
            }
            if (consultationsCount < minConsultations) {
                minConsultations = consultationsCount;
                medecinChoisi = medecin;
            }
        }
        return medecinChoisi;
    }

    @Override
    public ConsultationResponse createConsultation(ConsultationRequest request) {
        Consultation consultation = new Consultation();
        consultation.setMotif(request.getMotif());
        consultation.setDateConsultation(request.getDateConsultation());
        consultation.setTypeConsultation(request.getTypeConsultation());
        consultation.setStatus(statusConsultation.en_attente);  // Statut par défaut
        if ("generale".equals(request.getTypeConsultation())) {
            consultation.setCout(300.00f);
        } else {
            consultation.setCout(450.00f);
        }
        Medecin medecin = attachMedecinToConsultation(request.getTypeConsultation(), request.getDateConsultation());
        consultation.setMedecin(medecin);
        User currentUser = currentUserUtil.getCurrentUser();
        Patient patient = patientRepository.findByUser(currentUser);
        consultation.setPatient(patient);
        consultationRepository.save(consultation);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(request.getDateConsultation());
        emailSender.sendSimpleEmail(
                medecin.getUser().getEmail(),
                "Un rendez-vous a été attribué",
                "Vous avez un rendez-vous le " + formattedDate + " avec le patient "
                        + patient.getUser().getPrenom() + " " + patient.getUser().getNom()
        );
        return mapper.convertToConsultationResponse(consultation);
    }

    @Override
    public void deleteConsultation(Long id) {
        if (!consultationRepository.existsById(id)){
            throw new IllegalStateException("consultations avec cet id" + id + "n'existe pas");
        }
        consultationRepository.deleteById(id);

    }

    @Override
    public List<ConsultationResponse> getAllConsultations() {
        return consultationRepository.findAll()
                .stream()
                .map(mapper::convertToConsultationResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ConsultationResponse> getConsultationsByPatient(Patient patient) {
        List<Consultation> consultations = consultationRepository.findByPatient(patient);
        List<ConsultationResponse> consultationResponses = new ArrayList<>();
        for (Consultation consultation : consultations) {
            consultationResponses.add(mapper.convertToConsultationResponse(consultation));
        }
        return consultationResponses;
    }

    @Override
    public List<ConsultationResponse> getConsultationsByMedecin(Medecin medecin) {
        List<Consultation> consultations = consultationRepository.findByMedecin(medecin);
        List<ConsultationResponse> consultationResponses = new ArrayList<>();
        for (Consultation consultation : consultations) {
            consultationResponses.add(mapper.convertToConsultationResponse(consultation));
        }
        return consultationResponses;
    }

}
