package org.example.uml_hospital.Repositories;

import org.example.uml_hospital.Entities.Consultation;
import org.example.uml_hospital.Entities.Medecin;
import org.example.uml_hospital.Entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ConsultationRepository extends JpaRepository<Consultation,Long> {
    int  countByMedecinAndDateConsultation(Medecin medecin, Date date_consultation);
    List<Consultation> findByPatient(Patient patient);
    List<Consultation> findByMedecin(Medecin medecin);

}
