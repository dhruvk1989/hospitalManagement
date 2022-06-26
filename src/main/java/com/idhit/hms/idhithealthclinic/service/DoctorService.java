package com.idhit.hms.idhithealthclinic.service;

import com.idhit.hms.idhithealthclinic.entity.Appointment;
import com.idhit.hms.idhithealthclinic.entity.Department;
import com.idhit.hms.idhithealthclinic.entity.Doctor;
import com.idhit.hms.idhithealthclinic.exception.ResourceNotFoundException;
import com.idhit.hms.idhithealthclinic.payload.DoctorRequestPayload;
import com.idhit.hms.idhithealthclinic.repo.AppointmentRepo;
import com.idhit.hms.idhithealthclinic.repo.DepartmentRepo;
import com.idhit.hms.idhithealthclinic.repo.DoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jca.cci.RecordTypeNotSupportedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    DoctorRepo doctorRepo;

    @Autowired
    DepartmentRepo departmentRepo;

    @Autowired
    AppointmentRepo appointmentRepo;

    public List<Doctor> getAllDoctors(){
        return doctorRepo.findAll();
    }

    public Doctor getOneDoctor(Long id) {
        Doctor doctor;
        try {
            doctor = doctorRepo.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException("Doctor", id);
        }
        return doctor;
    }

    public Doctor createDoctor(DoctorRequestPayload doctorRP) {
        Doctor doctor = new Doctor();
        if(!doctorRP.getName().contains("Dr.")){
            doctorRP.setName("Dr. " + doctorRP.getName());
        }
        doctor.setName(doctorRP.getName());
        doctor.setAge(doctorRP.getAge());
        Optional<Department> department = null;
        try {
            department = departmentRepo.findDepartmentByDeptName(doctorRP.getDept());
        }catch(NoSuchElementException e){
            throw new ResourceNotFoundException("Department");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        doctor.setDept(department.get());
        Doctor savedDoctor = doctorRepo.save(doctor);
        return savedDoctor;
    }

    public String deleteDoctor(Long id) {
        if(doctorRepo.existsById(id) == false) {
            throw new ResourceNotFoundException("Doctor", id);
        }else{
            doctorRepo.deleteById(id);
        }
        return "Doctor with ID " + id + " has been successfully deleted.";
    }

    public List<Appointment> listAllAppointments(Long id) {
       if(doctorRepo.existsById(id) == false){
           throw new ResourceNotFoundException("Doctor", id);
       }else{
           return doctorRepo.findById(id).get().getAppointments();
       }
    }

    public Doctor updateDoctor(Long id, DoctorRequestPayload doctorRequestPayload) {
        if(!doctorRepo.existsById(id)){
            throw new ResourceNotFoundException("Doctor", id);
        }
        Doctor doctor = doctorRepo.findById(id).get();
        doctor.setDoctorId(id);
        if(doctorRequestPayload.getName() != null && doctorRequestPayload.getName().length() > 0){
            doctor.setName(doctorRequestPayload.getName());
        }
        if(doctorRequestPayload.getAge() != null && doctorRequestPayload.getAge().length() > 0){
            doctor.setAge(doctorRequestPayload.getAge());
        }
        if(doctorRequestPayload.getDept() != null && doctorRequestPayload.getDept().length() > 0){
            Optional<Department> departmentByDeptName = departmentRepo.findDepartmentByDeptName(doctorRequestPayload.getDept());
            departmentByDeptName.ifPresent((a) -> doctor.setDept(a));
        }
        return doctorRepo.save(doctor);
    }
}
