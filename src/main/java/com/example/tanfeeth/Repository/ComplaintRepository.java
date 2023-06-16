package com.example.tanfeeth.Repository;

import com.example.tanfeeth.Model.Complaint;
import com.example.tanfeeth.Model.InNeedCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint,Integer> {

    Complaint findComplaintById(Integer id);
    List<Complaint> findComplaintsByInNeedCompany(InNeedCompany inNeedCompany);

}
