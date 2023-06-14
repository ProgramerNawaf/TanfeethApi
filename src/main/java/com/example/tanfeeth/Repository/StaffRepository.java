package com.example.tanfeeth.Repository;

import com.example.tanfeeth.Model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff,Integer> {


    Staff findStaffById(Integer idStaff);

}
