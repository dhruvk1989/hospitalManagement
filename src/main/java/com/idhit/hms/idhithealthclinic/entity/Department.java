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
public class Department {

    @Id
    private Long deptId;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Doctor> doctor;

    private String deptName;

}
