package com.idhit.hms.idhithealthclinic.repo;

import com.idhit.hms.idhithealthclinic.entity.Doctor;
import com.idhit.hms.idhithealthclinic.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionRepo extends JpaRepository<Prescription, Long> {

    @Query(value = "Select * from prescription where doctor_doctor_id = ?1 and appointment_id = ?2", nativeQuery = true)
    public List<Prescription> findPrescriptionByDoctorAndAppointment(Long docId, Long apptId);

}
