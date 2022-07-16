package com.idhit.hms.idhithealthclinic.service;

import com.idhit.hms.idhithealthclinic.entity.Appointment;
import com.idhit.hms.idhithealthclinic.entity.Doctor;
import com.idhit.hms.idhithealthclinic.entity.Prescription;
import com.idhit.hms.idhithealthclinic.exception.ResourceNotFoundException;
import com.idhit.hms.idhithealthclinic.payload.PrescriptionRequestPayload;
import com.idhit.hms.idhithealthclinic.repo.AppointmentRepo;
import com.idhit.hms.idhithealthclinic.repo.DoctorRepo;
import com.idhit.hms.idhithealthclinic.repo.PrescriptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class PrescriptionService {

    @Autowired
    PrescriptionRepo prescriptionRepo;

    @Autowired
    AppointmentRepo appointmentRepo;

    @Autowired
    DoctorRepo doctorRepo;

    public List<Prescription> getAllPrescriptions(Long id, Long apptId) {
        if(!doctorRepo.existsById(id)){
            throw new ResourceNotFoundException("Doctor", id);
        }
        if(!appointmentRepo.existsById(apptId)){
            throw new ResourceNotFoundException("Appointment", apptId);
        }

        return prescriptionRepo.findPrescriptionByDoctorAndAppointment(id, apptId);
    }

    public List<Prescription> getAllPrescriptionsDoctor(Long docId){
        if(!doctorRepo.existsById(docId)){
            throw new ResourceNotFoundException("Doctor", docId);
        }

        return prescriptionRepo.findPrescriptionByDoctor(docId);
    }

    public Prescription createPrescription(Long docId, Long apptId, PrescriptionRequestPayload prescriptionRP){
        Prescription prescription = new Prescription();
        if(!doctorRepo.existsById(docId)){
            throw new ResourceNotFoundException("Doctor", docId);
        }
        if(!appointmentRepo.existsById(apptId)){
            throw new ResourceNotFoundException("Appointment", apptId);
        }
        Appointment appointment = appointmentRepo.findById(apptId).get();
        Doctor doctor = doctorRepo.findById(docId).get();

        prescription.setAppointment(appointment);
        prescription.setDoctor(doctor);
        prescription.setMedicines(prescriptionRP.getMedicines());
        prescription.setDoctorRemarks(prescriptionRP.getDoctorRemarks());
        prescription = prescriptionRepo.save(prescription);

        appointment.setStatus("Prescribed");
        appointment.setPrescriptionId(prescription.getPrescriptionId());
        appointmentRepo.save(appointment);

        return prescription;

    }

    public Prescription getOnePrescription(Long docId, Long apptId, Long pId) {
        if(!doctorRepo.existsById(docId)){
            throw new ResourceNotFoundException("Doctor", docId);
        }
        if(!appointmentRepo.existsById(apptId)){
            throw new ResourceNotFoundException("Appointment", apptId);
        }
        if(!prescriptionRepo.existsById(pId)){
            throw new ResourceNotFoundException("Prescription", pId);
        }
        return prescriptionRepo.findById(pId).get();
    }

    public String deletePrescription(Long docId, Long apptId, Long pId) {
        if(!doctorRepo.existsById(docId)){
            throw new ResourceNotFoundException("Doctor", docId);
        }
        if(!appointmentRepo.existsById(apptId)){
            throw new ResourceNotFoundException("Appointment", apptId);
        }
        if(!prescriptionRepo.existsById(pId)){
            throw new ResourceNotFoundException("Prescription", pId);
        }
        Prescription prescription = prescriptionRepo.findById(pId).get();
        prescriptionRepo.deleteById(pId);
        return "The prescription of " + prescription.getAppointment().getPatientName() + " has been deleted";
    }

    public Prescription updatePrescription(Long docId, Long apptId, Long pId, PrescriptionRequestPayload prescriptionRequestPayload) {
        if(!doctorRepo.existsById(docId)){
            throw new ResourceNotFoundException("Doctor", docId);
        }
        if(!appointmentRepo.existsById(apptId)){
            throw new ResourceNotFoundException("Appointment", apptId);
        }
        if(!prescriptionRepo.existsById(pId)){
            throw new ResourceNotFoundException("Prescription", pId);
        }

        Prescription prescription = prescriptionRepo.findById(pId).get();
        prescription.setPrescriptionId(pId);
        if(prescriptionRequestPayload.getMedicines() != null && prescriptionRequestPayload.getMedicines().length() > 0){
            prescription.setMedicines(prescriptionRequestPayload.getMedicines());
        }
        if(prescriptionRequestPayload.getDoctorRemarks() != null && prescriptionRequestPayload.getDoctorRemarks().length() > 0){
            prescription.setDoctorRemarks(prescriptionRequestPayload.getDoctorRemarks());
        }

        return prescriptionRepo.save(prescription);
    }
}
