package com.idhit.hms.idhithealthclinic.entity;

import javax.persistence.*;

@Entity
@Table(name = "prescription")
public class Prescription {
    
    @Id
    private Long prescriptionId;

    @ManyToOne
    private Doctor doctor;

    @OneToOne
    private Appointment appointment;
    
}
