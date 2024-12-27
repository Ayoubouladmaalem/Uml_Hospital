package org.example.uml_hospital.Repositories;

import org.example.uml_hospital.Entities.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MedecinRepository extends JpaRepository<Medecin, Long> {
    List<Medecin> getMedecinsBySpecialite(String specialite);
}
