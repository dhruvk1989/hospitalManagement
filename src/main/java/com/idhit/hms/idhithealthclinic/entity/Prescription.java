package com.idhit.hms.idhithealthclinic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "prescription")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Prescription {
    
    @Id
    @GeneratedValue
    private Long prescriptionId;

    @ManyToOne
    @JsonIgnore
    private Doctor doctor;

    @OneToOne
    private Appointment appointment;

    private String medicines;
    
}
