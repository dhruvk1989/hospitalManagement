package com.idhit.hms.idhithealthclinic.controller;

import com.idhit.hms.idhithealthclinic.entity.Appointment;
import com.idhit.hms.idhithealthclinic.entity.Doctor;
import com.idhit.hms.idhithealthclinic.exception.ResourceNotFoundException;
import com.idhit.hms.idhithealthclinic.payload.DoctorRequestPayload;
import com.idhit.hms.idhithealthclinic.payload.Schedule;
import com.idhit.hms.idhithealthclinic.repo.DepartmentRepo;
import com.idhit.hms.idhithealthclinic.repo.DoctorRepo;
import com.idhit.hms.idhithealthclinic.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DoctorController {

    @Autowired
    DoctorRepo doctorRepo;

    @Autowired
    DoctorService doctorService;

    @GetMapping("/doctors")
    public List<Doctor> getAllDoctors(){
        return doctorService.getAllDoctors();
    }

    @GetMapping("/doctors/{id}")
    public Doctor getOneDoctor(@PathVariable Long id){
        return doctorService.getOneDoctor(id);
    }

    @PostMapping("/doctors")
    public Doctor createDoctor(@RequestBody DoctorRequestPayload doctorRP){
        return doctorService.createDoctor(doctorRP);
    }

    @DeleteMapping("/doctors/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable Long id){
        String result = doctorService.deleteDoctor(id);
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }

    @GetMapping("/doctors/emailSearch")
    public Doctor findByEmail(@RequestParam("email") String email){
        Doctor doctor;
        try{
            doctor = doctorRepo.findDoctorByUserName(email).get();
        }catch (Exception e){
            throw new ResourceNotFoundException("Doctor");
        }
        return doctor;
    }

    @GetMapping("doctors/{id}/appointments")
    public List<Appointment> listAllAppointmentOfTheDoctor(@PathVariable Long id){
        return doctorService.listAllAppointments(id);
    }

    @GetMapping("doctors/{id}/schedule")
    public List<Schedule> listAllSchedules(@PathVariable Long id){
        return doctorService.listDoctorSchedule(id);
    }

    @PutMapping("doctors/{id}")
    public Doctor updateDoctor(@PathVariable Long id,
                               @RequestBody DoctorRequestPayload doctorRequestPayload){
        return doctorService.updateDoctor(id, doctorRequestPayload);
    }

}
