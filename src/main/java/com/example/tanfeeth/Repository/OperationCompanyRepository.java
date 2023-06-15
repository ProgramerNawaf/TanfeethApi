package com.example.tanfeeth.Repository;

import com.example.tanfeeth.Model.OperationCompany;
import com.example.tanfeeth.Model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationCompanyRepository extends JpaRepository<OperationCompany,Integer> {

    OperationCompany findOperationCompanyById(Integer id);



}
