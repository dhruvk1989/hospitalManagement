package com.idhit.hms.idhithealthclinic.controller;

import com.idhit.hms.idhithealthclinic.entity.Prescription;
import com.idhit.hms.idhithealthclinic.payload.PrescriptionRequestPayload;
import com.idhit.hms.idhithealthclinic.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PrescriptionController {

    @Autowired
    PrescriptionService prescriptionService;

    @GetMapping("/doctors/{docId}/appointments/{apptId}/prescriptions")
    public List<Prescription> listPrescriptions(@PathVariable Long docId,
                                                @PathVariable Long apptId){
        return prescriptionService.getAllPrescriptions(docId, apptId);
    }

    @PostMapping("/doctors/{docId}/appointments/{apptId}/prescriptions")
    public ResponseEntity<String> createPrescription(@PathVariable Long docId,
                                                     @PathVariable Long apptId,
                                                     @RequestBody PrescriptionRequestPayload prescriptionRequestPayload){
        String result = prescriptionService.createPrescription(docId, apptId, prescriptionRequestPayload);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/doctors/{docId}/appointments/{apptId}/prescriptions/{pId}")
    public Prescription getOnePrescription(@PathVariable Long docId,
                                                  @PathVariable Long apptId,
                                                  @PathVariable Long pId){
        return prescriptionService.getOnePrescription(docId, apptId, pId);
    }

    @DeleteMapping("/doctors/{docId}/appointments/{apptId}/prescriptions/{pId}")
    public String deletePrescription(@PathVariable Long docId,
                                           @PathVariable Long apptId,
                                           @PathVariable Long pId){
        return prescriptionService.deletePrescription(docId, apptId, pId);
    }

    @PutMapping("/doctors/{docId}/appointments/{apptId}/prescriptions/{pId}")
    public Prescription updatePrescription(@PathVariable Long docId,
                                           @PathVariable Long apptId,
                                           @PathVariable Long pId,
                                           @RequestBody PrescriptionRequestPayload prescriptionRequestPayload){
        return prescriptionService.updatePrescription(docId, apptId, pId, prescriptionRequestPayload);
    }

}
