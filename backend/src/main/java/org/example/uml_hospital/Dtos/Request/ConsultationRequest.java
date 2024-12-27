package org.example.uml_hospital.Dtos.Request;


import lombok.Data;

import java.util.Date;

@Data
public class ConsultationRequest {
    private String motif;
    private String typeConsultation;
    private Date dateConsultation;
}
