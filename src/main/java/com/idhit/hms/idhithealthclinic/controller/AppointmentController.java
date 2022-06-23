package com.idhit.hms.idhithealthclinic.controller;

import com.idhit.hms.idhithealthclinic.entity.Appointment;
import com.idhit.hms.idhithealthclinic.entity.Doctor;
import com.idhit.hms.idhithealthclinic.exception.ResourceNotFoundException;
import com.idhit.hms.idhithealthclinic.payload.AppointmentRequestPayload;
import com.idhit.hms.idhithealthclinic.repo.AppointmentRepo;
import com.idhit.hms.idhithealthclinic.repo.DoctorRepo;
import com.idhit.hms.idhithealthclinic.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    AppointmentRepo appointmentRepo;

    @GetMapping("/appointments")
    public List<Appointment> getAllAppointment(){
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/appointments/{id}")
    public Appointment getOneAppointment(@PathVariable("id") Long id){
        Appointment appointment = appointmentService.getOneAppointment(id);
        if(appointment == null){
            throw new ResourceNotFoundException("Appointment", id);
        }else{
            return appointment;
        }
    }

    @DeleteMapping("/appointments/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable("id") Long id){
        if(appointmentRepo.existsById(id) == false){
            throw new ResourceNotFoundException("Appointment", id);
        }else{
            appointmentService.deleteAppointment(id);
            return new ResponseEntity<String>("The appointment with appointment ID " + id + " has been deleted",
                    HttpStatus.OK);
        }
    }

    @PostMapping("appointments")
    public ResponseEntity<String> createAppointment(@RequestBody AppointmentRequestPayload payload){
        return new ResponseEntity<String>(appointmentService.createAppointment(payload), HttpStatus.CREATED);
    }

    @PutMapping("appointments/{id}")
    public Appointment updateAppointment(@PathVariable Long id,
                                         @RequestBody AppointmentRequestPayload appointmentRequestPayload){
        return appointmentService.updateAppointment(id, appointmentRequestPayload);
    }

}
