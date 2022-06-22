package com.idhit.hms.idhithealthclinic.service;

import com.idhit.hms.idhithealthclinic.entity.Medicine;
import com.idhit.hms.idhithealthclinic.exception.ResourceNotFoundException;
import com.idhit.hms.idhithealthclinic.repo.MedicineRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineService {

    @Autowired
    MedicineRepo medicineRepo;

    public List<Medicine> getAllMedicines() {
        return medicineRepo.findAll();
    }

    public Medicine getOneMedicine(Long id) {
        if(!medicineRepo.existsById(id)){
            throw new ResourceNotFoundException("Medicine", id);
        }
        return medicineRepo.findById(id).get();
    }

    public String createMedicineRecord(Medicine medicine){
        medicineRepo.save(medicine);
        return "Medicine record has been added successfully.";
    }

    public String deleteMedicines(Long id) {
        if(!medicineRepo.existsById(id)){
            throw new ResourceNotFoundException("Medicine", id);
        }
        medicineRepo.deleteById(id);
        return "Medicine record has been deleted.";
    }

    public Medicine updateMedicine(Long id, Medicine medicine){
        if(!medicineRepo.existsById(id)){
            throw new ResourceNotFoundException("Medicine", id);
        }
        medicine.setId(id);
        return medicineRepo.save(medicine);
    }

}
