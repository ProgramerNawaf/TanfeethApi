package com.example.tanfeeth.Repository;

import com.example.tanfeeth.Model.InNeedCompany;
import com.example.tanfeeth.Model.OperationCompany;
import com.example.tanfeeth.Model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request,Integer> {

    List<Request> findRequestsByOperationCompany(OperationCompany operationCompany);
    List<Request> findRequestsByInNeedCompany(InNeedCompany inNeedCompany);

    Request findRequestById(Integer id);
}
