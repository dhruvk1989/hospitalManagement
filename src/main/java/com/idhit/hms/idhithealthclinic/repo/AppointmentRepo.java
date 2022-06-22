package com.idhit.hms.idhithealthclinic.repo;

import com.idhit.hms.idhithealthclinic.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Long> {

}
