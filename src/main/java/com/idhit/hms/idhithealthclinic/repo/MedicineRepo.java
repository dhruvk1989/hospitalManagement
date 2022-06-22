package com.idhit.hms.idhithealthclinic.repo;

import com.idhit.hms.idhithealthclinic.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepo extends JpaRepository<Medicine, Long> {

    @Query(value = "SELECT COUNT(*) FROM medicine m WHERE medicine_name IN (?1)", nativeQuery = true)
    public Integer countMedicines(String medicines);

}
