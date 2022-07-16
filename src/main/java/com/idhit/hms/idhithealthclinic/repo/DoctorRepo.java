package com.idhit.hms.idhithealthclinic.repo;

import com.idhit.hms.idhithealthclinic.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DoctorRepo extends JpaRepository<Doctor, Long> {

    @Query(value = "select * from doctor where dept_dept_id = (select d.dept_id from department d where d.dept_name = ?1)",
            nativeQuery = true)
    public Doctor getDoctorByDeptJoin(String dept);

    public Optional<Doctor> findDoctorByUserName(String email);

}
