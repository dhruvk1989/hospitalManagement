package com.idhit.hms.idhithealthclinic.repo;

import com.idhit.hms.idhithealthclinic.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long> {
    Optional<Department> findDepartmentByDeptName(String dept);
}
