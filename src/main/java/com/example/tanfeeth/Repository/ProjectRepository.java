package com.example.tanfeeth.Repository;

import com.example.tanfeeth.Model.InNeedCompany;
import com.example.tanfeeth.Model.OperationCompany;
import com.example.tanfeeth.Model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Integer> {

    Project findProjectById(Integer id);
    List<Project> findProjectsByInNeedCompany(InNeedCompany inNeedCompany);
    List<Project> findProjectsByStatus(String status);
    List<Project> findProjectsByOperationCompany(OperationCompany operationCompany);

    Project findProjectByName(String name);

}
