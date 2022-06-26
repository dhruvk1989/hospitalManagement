package com.idhit.hms.idhithealthclinic.service;

import com.idhit.hms.idhithealthclinic.entity.Appointment;
import com.idhit.hms.idhithealthclinic.entity.Department;
import com.idhit.hms.idhithealthclinic.entity.Doctor;
import com.idhit.hms.idhithealthclinic.exception.ResourceNotFoundException;
import com.idhit.hms.idhithealthclinic.payload.AppointmentRequestPayload;
import com.idhit.hms.idhithealthclinic.payload.AppointmentResponsePayload;
import com.idhit.hms.idhithealthclinic.payload.DoctorAppointments;
import com.idhit.hms.idhithealthclinic.repo.AppointmentRepo;
import com.idhit.hms.idhithealthclinic.repo.DepartmentRepo;
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

    @Autowired
    private DepartmentRepo departmentRepo;

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

    public Appointment createAppointment(AppointmentRequestPayload apptReqPayload){
        Appointment appointment = new Appointment();
        appointment.setSymptoms(apptReqPayload.getSymptoms());
        appointment.setAppointmentDateTime(new Date());
        appointment.setStatus("Open");
        appointment.setAge(apptReqPayload.getAge());
        appointment.setGender(apptReqPayload.getGender());
        appointment.setPatientName(apptReqPayload.getName());

        String symptoms = apptReqPayload.getSymptoms();

        Doctor assignedDoctor = null;
        Department department = null;

        if(symptoms.toLowerCase().contains("heart") || symptoms.toLowerCase().contains("blood pressure")
            || symptoms.toLowerCase().contains("breathlessness") || symptoms.toLowerCase().contains("palpitation")
            || symptoms.toLowerCase().contains("pain in left shoulder")){
            department = departmentRepo.findDepartmentByDeptName("Cardiologist").get();
        }else if(symptoms.toLowerCase().contains("ear") || symptoms.toLowerCase().contains("nose")
                || symptoms.toLowerCase().contains("throat")){
            department = departmentRepo.findDepartmentByDeptName("ENT").get();
        }else if(symptoms.toLowerCase().contains("eye") || symptoms.toLowerCase().contains("sight")
                || symptoms.toLowerCase().contains("vision")){
            department = departmentRepo.findDepartmentByDeptName("Opthamologist").get();
        }else if(symptoms.toLowerCase().contains("hearing problem") || symptoms.toLowerCase().contains("sound")
                || symptoms.toLowerCase().contains("ear problem")){
            department = departmentRepo.findDepartmentByDeptName("Audiologist").get();
        }else if(symptoms.toLowerCase().contains("fever") || symptoms.toLowerCase().contains("cold")
                || symptoms.toLowerCase().contains("cough")){
            department = departmentRepo.findDepartmentByDeptName("General Physician").get();
        }

        List<DoctorAppointments> doctorAppointments = appointmentRepo.getDoctorAppointmentByDeptId(department.getDeptId());
        DoctorAppointments doctorAppointments1 = doctorAppointments.stream().min((a, b) -> a.compareTo(b)).get();
        assignedDoctor = doctorRepo.findById(Long.parseLong(""+doctorAppointments1.getDoctorId())).get();

        appointment.setDoctor(assignedDoctor);
        if(assignedDoctor != null){
            appointment.setDoctorName(assignedDoctor.getName());
        }else{
            appointment.setDoctorName("None");
        }
        Appointment save = appointmentRepo.save(appointment);

        return save;

    }

    public Appointment updateAppointment(Long id, AppointmentRequestPayload appointmentRequestPayload) {
        if(!appointmentRepo.existsById(id)){
            throw new ResourceNotFoundException("Appointment", id);
        }
        Appointment appointment = appointmentRepo.findById(id).get();
        if(appointmentRequestPayload.getName() != null && appointmentRequestPayload.getName().length() > 0){
            appointment.setPatientName(appointmentRequestPayload.getName());
        }
        if(appointmentRequestPayload.getAge() != null && appointmentRequestPayload.getAge() > 0){
            appointment.setAge(appointmentRequestPayload.getAge());
        }
        if(appointmentRequestPayload.getSymptoms() != null && appointmentRequestPayload.getSymptoms().length() > 0){
            appointment.setSymptoms(appointmentRequestPayload.getSymptoms());
        }
        if(appointmentRequestPayload.getGender() != null && appointmentRequestPayload.getGender().length() > 0){
            appointment.setGender(appointmentRequestPayload.getGender());
        }
        return appointmentRepo.save(appointment);
    }
}
