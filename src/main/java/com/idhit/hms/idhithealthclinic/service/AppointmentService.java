package com.idhit.hms.idhithealthclinic.service;

import com.idhit.hms.idhithealthclinic.entity.Appointment;
import com.idhit.hms.idhithealthclinic.entity.Department;
import com.idhit.hms.idhithealthclinic.entity.Doctor;
import com.idhit.hms.idhithealthclinic.exception.ResourceNotFoundException;
import com.idhit.hms.idhithealthclinic.payload.AppointmentRequestPayload;
import com.idhit.hms.idhithealthclinic.repo.AppointmentRepo;
import com.idhit.hms.idhithealthclinic.repo.DoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AppointmentService {

    @Autowired
    AppointmentRepo appointmentRepo;

    @Autowired
    DoctorRepo doctorRepo;

    public List<Appointment> getAllAppointments(){
        return appointmentRepo.findAll();
    }

    public Appointment getOneAppointment(Long id){
        Appointment appt;
        try {
            appt = appointmentRepo.findById(id).get();
        }catch(NoSuchElementException e){
            throw new ResourceNotFoundException("Appointment", id);
        }
        return appt;
    }

    public void deleteAppointment(Long id){
        if(appointmentRepo.existsById(id) == false){
            throw new ResourceNotFoundException("Appointment", id);
        }
        appointmentRepo.deleteById(id);
    }

    public String createAppointment(AppointmentRequestPayload apptReqPayload){
        Appointment appointment = new Appointment();
        appointment.setSymptoms(apptReqPayload.getSymptoms());
        appointment.setAppointmentDateTime(new Date());
        appointment.setStatus("Open");
        appointment.setAge(apptReqPayload.getAge());
        appointment.setGender(apptReqPayload.getGender());
        appointment.setPatientName(apptReqPayload.getName());

        String symptoms = apptReqPayload.getSymptoms();

        Doctor assignedDoctor = null;

        if(symptoms.toLowerCase().contains("heart") || symptoms.toLowerCase().contains("blood pressure")
            || symptoms.toLowerCase().contains("breathlessness") || symptoms.toLowerCase().contains("palpitation")
            || symptoms.toLowerCase().contains("pain in left shoulder")){
            assignedDoctor = doctorRepo.getDoctorByDeptJoin("Cardiologist");
        }else if(symptoms.toLowerCase().contains("ear") || symptoms.toLowerCase().contains("nose")
                || symptoms.toLowerCase().contains("throat")){
            assignedDoctor = doctorRepo.getDoctorByDeptJoin("ENT");
        }else if(symptoms.toLowerCase().contains("eye") || symptoms.toLowerCase().contains("sight")
                || symptoms.toLowerCase().contains("vision")){
            assignedDoctor = doctorRepo.getDoctorByDeptJoin("Opthamologist");
        }else if(symptoms.toLowerCase().contains("hearing problem") || symptoms.toLowerCase().contains("sound")
                || symptoms.toLowerCase().contains("ear problem")){
            assignedDoctor = doctorRepo.getDoctorByDeptJoin("Audiologist");
        }else if(symptoms.toLowerCase().contains("fever") || symptoms.toLowerCase().contains("cold")
                || symptoms.toLowerCase().contains("cough")){
            assignedDoctor = doctorRepo.getDoctorByDeptJoin("General Physician");
        }

        appointment.setDoctor(assignedDoctor);
        Appointment save = appointmentRepo.save(appointment);

        if(assignedDoctor == null){
            return "No doctor was found at the moment.";
        }else{
            return "You have been assigned to " + assignedDoctor.getName() + ". Your appointment ID is " + save.getId();
        }

    }

    public Appointment updateAppointment(Long id, AppointmentRequestPayload appointmentRequestPayload) {
        if(!appointmentRepo.existsById(id)){
            throw new ResourceNotFoundException("Appointment", id);
        }
        Appointment appointment = appointmentRepo.findById(id).get();
        if(appointmentRequestPayload.getName() != null || appointmentRequestPayload.getName().length() > 0){
            appointment.setPatientName(appointmentRequestPayload.getName());
        }
        if(appointmentRequestPayload.getAge() != null || appointmentRequestPayload.getAge() > 0){
            appointment.setAge(appointmentRequestPayload.getAge());
        }
        if(appointmentRequestPayload.getSymptoms() != null || appointmentRequestPayload.getSymptoms().length() > 0){
            appointment.setSymptoms(appointmentRequestPayload.getSymptoms());
        }
        if(appointmentRequestPayload.getGender() != null || appointmentRequestPayload.getGender().length() > 0){
            appointment.setGender(appointmentRequestPayload.getGender());
        }
        return appointmentRepo.save(appointment);
    }
}
