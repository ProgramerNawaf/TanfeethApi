package com.example.tanfeeth.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationCompanyRepository extends JpaRepository<OperationCompanyRepository,Integer> {
}
