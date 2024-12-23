package org.example.uml_hospital.Repositories;

import org.example.uml_hospital.Entities.Medecin;
import org.example.uml_hospital.Entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
