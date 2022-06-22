package com.idhit.hms.idhithealthclinic.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Patient {

    @Id
    private Long patientId;

    @OneToOne
    private Appointment appointment;

    private String name;

    private String gender;

    private Integer age;

    private String symptomps;

}
