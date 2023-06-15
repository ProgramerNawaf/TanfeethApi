package com.example.tanfeeth.Repository;

import com.example.tanfeeth.Model.OperationCompany;
import com.example.tanfeeth.Model.Project;
import com.example.tanfeeth.Model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff,Integer> {


    Staff findStaffById(Integer idStaff);
    List<Staff> findStaffByOperationCompany(OperationCompany operationCompany);
    List <Staff> findStaffByProject(Project project);
    List<Staff> findStaffByStatus(String status);




}
