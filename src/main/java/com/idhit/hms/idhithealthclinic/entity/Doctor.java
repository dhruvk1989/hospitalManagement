package com.idhit.hms.idhithealthclinic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long doctorId;

    private String name;

    private String age;

    @ManyToOne(cascade = CascadeType.DETACH)
    private Department dept;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Prescription> prescriptionList;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Appointment> appointments;

    private String userName;

    @Override
    public String toString() {
        return "Doctor{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}



