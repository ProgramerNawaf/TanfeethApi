package com.example.tanfeeth.Service;

import com.example.tanfeeth.ApiException.ApiException;
import com.example.tanfeeth.Model.InNeedCompany;
import com.example.tanfeeth.Model.MyUser;
import com.example.tanfeeth.Model.Project;
import com.example.tanfeeth.Model.Staff;
import com.example.tanfeeth.Repository.InNeedCompanyRepository;
import com.example.tanfeeth.Repository.MyUserRepositroy;
import com.example.tanfeeth.Repository.OperationCompanyRepository;
import com.example.tanfeeth.Repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final MyUserRepositroy myUserRepositroy;
    private final OperationCompanyRepository operationCompanyRepository;
    private final InNeedCompanyRepository inNeedCompanyRepository;

    public List<Project> getAll(){
        return projectRepository.findAll();
    }

    public Set<Project> getCompanyProject(Integer companyId){
        MyUser user =myUserRepositroy.findMyUsersById(companyId);
        if(user == null)
            new ApiException("Company dosen't exist!");
        if(user.getInNeedCompany() == null){
            changeStatusForProjectToDelayed(companyId);
            return user.getOperationCompany().getProjectSet();
        }
        else{
            changeStatusForProjectToDelayed(companyId);
            return user.getInNeedCompany().getProjectSet();
        }
    }

    public Set<Project> getProjectsByCompanyId(Integer id){
        MyUser user =myUserRepositroy.findMyUsersById(id);
        if(user == null)
            new ApiException("Company dosen't exist!");
        //تحديد نوع الشركة
        if(user.getInNeedCompany() != null){
           return user.getInNeedCompany().getProjectSet();
        }else{
            return user.getOperationCompany().getProjectSet();
        }

    }

    public void addProject(Project p , Integer id){
        MyUser user =myUserRepositroy.findMyUsersById(id);

        //تحديد نوع الشركة
//        InNeedCompany inNeedCompany = user.getInNeedCompany();
            p.setInNeedCompany(user.getInNeedCompany());
            if(p.getStatus() == null)
               p.setStatus("NEW");
            user.getInNeedCompany().getProjectSet().add(p);
            projectRepository.save(p);

    }
    public void deleteProject(Integer idIN ,Integer projectId){
        Project project =projectRepository.findProjectById(projectId);
        MyUser user = myUserRepositroy.findMyUsersById(idIN);
        if (project == null || project.getInNeedCompany().getId()!= user.getId()){
            throw new ApiException("Project with this ID dosen't exist!");
        }
        if(project.getOperationCompany()!=null)
            throw new ApiException("Can't delete this project is assigned to "+project.getOperationCompany().getName()+" !");
            projectRepository.delete(project);
    }

    public void changeStatusForProjectToDelayed(Integer idInNeed){
        MyUser user = myUserRepositroy.findMyUsersById(idInNeed);
        InNeedCompany inNeedCompany = user.getInNeedCompany();
        List<Project> project = projectRepository.findProjectsByInNeedCompany(inNeedCompany);
        for (int i =0 ; i<project.size();i++){
            if (project.get(i).getEndDate().isAfter(LocalDateTime.now())&& !(project.get(i).getStatus().equalsIgnoreCase("FINISHED"))){
                project.get(i).setStatus("DELAYED");
                projectRepository.save(project.get(i));
            }
        }
    }

    public List<Project> getAllDelayedProject(Integer idInNeed){
        MyUser user = myUserRepositroy.findMyUsersById(idInNeed);
        InNeedCompany inNeedCompany = user.getInNeedCompany();
        List<Project> project = projectRepository.findProjectsByInNeedCompany(inNeedCompany);
        List<Project> projectDELAYED = new ArrayList<>();
        for (int i =0 ; i<project.size();i++){
            if (project.get(i).getStatus().equalsIgnoreCase("DELAYED")){
                projectDELAYED.add(project.get(i));
            }
        }
        return projectDELAYED;
    }




}
