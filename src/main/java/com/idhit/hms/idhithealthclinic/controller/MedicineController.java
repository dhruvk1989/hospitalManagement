package com.idhit.hms.idhithealthclinic.controller;

import com.idhit.hms.idhithealthclinic.entity.Medicine;
import com.idhit.hms.idhithealthclinic.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MedicineController {

    @Autowired
    MedicineService medicineService;

    @GetMapping("/medicines")
    public List<Medicine> getAllMedicines(){
        return medicineService.getAllMedicines();
    }

    @GetMapping("/medicines/{id}")
    public Medicine getOneMedicine(@PathVariable Long id){
        return medicineService.getOneMedicine(id);
    }

    @PostMapping("/medicines")
    public ResponseEntity<String> createMedicine(@RequestBody Medicine medicine){
        String result = medicineService.createMedicineRecord(medicine);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping("medicines/{id}")
    public ResponseEntity<String> deleteMedicine(@PathVariable Long id){
        String result = medicineService.deleteMedicines(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("medicines/{id}")
    public Medicine updateMedicine(@PathVariable Long id, @RequestBody Medicine medicine){
        return medicineService.updateMedicine(id, medicine);
    }

}
