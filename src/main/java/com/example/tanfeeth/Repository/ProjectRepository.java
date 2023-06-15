package com.example.tanfeeth.Repository;

import com.example.tanfeeth.Model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Integer> {

    Project findProjectById(Integer id);
    List<Project> findProjectsByInNeedCompany(InNeedCompany inNeedCompany);
    List<Project> findProjectsByStatus(String status);
}
