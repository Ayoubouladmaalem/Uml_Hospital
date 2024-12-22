package org.example.uml_hospital.Repositories;

import org.example.uml_hospital.Entities.Pharmacien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PharmacienRepository extends JpaRepository<Pharmacien, Long> {
}
