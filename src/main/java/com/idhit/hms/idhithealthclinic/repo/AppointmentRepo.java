package com.idhit.hms.idhithealthclinic.repo;

import com.idhit.hms.idhithealthclinic.entity.Appointment;
import com.idhit.hms.idhithealthclinic.entity.Doctor;
import com.idhit.hms.idhithealthclinic.payload.DoctorAppointments;
import com.idhit.hms.idhithealthclinic.payload.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.AbstractJpaQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Long> {

    @Query(value = "select count(*) AS appointmentCount, doctor_doctor_id AS doctorId from appointment \n" +
            "where doctor_doctor_id IN \n" +
            "(select f.doctor_id from doctor f where f.dept_dept_id = ?1) \n" +
            "group by doctor_doctor_id order by doctor_doctor_id", nativeQuery = true)
    List<DoctorAppointments> getDoctorAppointmentByDeptId(Long id);

    @Query(value = "select id AS apptId, appointment_date AS apptDate, doctor_doctor_id AS docId, doctor_name AS docName, appointment_time AS docTime, patient_name AS patientName \n" +
    "from appointment where doctor_doctor_id = ?1", nativeQuery = true)
    List<Schedule> getAppointmentsByDoctor(Long doctorId);

}
