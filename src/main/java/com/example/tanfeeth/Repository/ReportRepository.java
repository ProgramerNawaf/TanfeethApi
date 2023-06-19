package com.example.tanfeeth.Repository;


import com.example.tanfeeth.Model.Project;
import com.example.tanfeeth.Model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report,Integer> {


    List<Report> findReportsByProject(Project project);


}
