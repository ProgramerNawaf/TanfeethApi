package com.example.tanfeeth.Repository;

import com.example.tanfeeth.Model.InNeedCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InNeedCompanyRepository extends JpaRepository<InNeedCompany,Integer> {
}
