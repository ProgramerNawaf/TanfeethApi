package com.example.tanfeeth.Repository;

import com.example.tanfeeth.Model.OperationCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationCompanyRepository extends JpaRepository<OperationCompany,Integer> {
}
