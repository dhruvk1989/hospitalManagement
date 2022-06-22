package com.idhit.hms.idhithealthclinic.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Doctor {

    @Id
    private Long doctorId;

    private String name;

    private String age;

    @ManyToOne
    private Department dept;

    @OneToMany(mappedBy = "doctor")
    private List<Prescription> prescriptionList;

    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointments;

}
