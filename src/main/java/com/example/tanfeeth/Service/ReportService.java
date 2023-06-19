package com.example.tanfeeth.Service;


import com.example.tanfeeth.ApiException.ApiException;
import com.example.tanfeeth.Model.MyUser;
import com.example.tanfeeth.Model.OperationCompany;
import com.example.tanfeeth.Model.Project;
import com.example.tanfeeth.Model.Report;
import com.example.tanfeeth.Repository.MyUserRepositroy;
import com.example.tanfeeth.Repository.OperationCompanyRepository;
import com.example.tanfeeth.Repository.ProjectRepository;
import com.example.tanfeeth.Repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {


    private final ReportRepository reportRepository;
    private final OperationCompanyRepository operationCompanyRepository;
    private final ProjectRepository projectRepository;
    private final MyUserRepositroy myUserRepositroy;



    public List<Report> getAll(){
        List<Report> reports = reportRepository.findAll();
        return reports;
    }

    public void addReport(Integer idOC,Integer idProject,Report report){

        OperationCompany operationCompany = operationCompanyRepository.findOperationCompanyById(idOC);
        Project project = projectRepository.findProjectById(idProject);
       if (project == null){
           throw new ApiException("do not have this project");
       }
        if (project.getOperationCompany().getId()!=operationCompany.getId()){
            throw new ApiException("do not have any project by this id");
        }
        report.setProject(project);
        report.setReportDate(LocalDateTime.now());
        project.getReports().add(report);
        reportRepository.save(report);
    }

    public List<Report> getReportForProject(Integer id,Integer idProject){
        List<Report> reports = new ArrayList<>();
        MyUser user = myUserRepositroy.findMyUsersById(id);
        Project project = projectRepository.findProjectById(idProject);
        if (project == null){
            throw new ApiException("do not have this project");
        }
        if (user.getOperationCompany()!=null){
            if (project.getOperationCompany().getId()!=id || project.getOperationCompany()==null){
                throw new ApiException("do not have any project by this id");
            }
             reports = reportRepository.findReportsByProject(project);

        }else {
            if (project.getInNeedCompany().getId()!= id){
                throw new ApiException("do not have any project for this id");
            }
            reports = reportRepository.findReportsByProject(project);
        }
        return reports;
    }



}
