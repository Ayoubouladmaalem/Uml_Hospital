package org.example.uml_hospital.Dtos.Response;


import lombok.Data;

import java.util.Date;

@Data
public class ConsultationResponse {
    private Long id;
    private String motif;
    private String typeConsultation;
    private Date dateConsultation;
    private String MedecinName;
    private String PatientName;
}
