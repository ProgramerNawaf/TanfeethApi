package com.example.tanfeeth.Service;

import com.example.tanfeeth.ApiException.ApiException;
import com.example.tanfeeth.Model.InNeedCompany;
import com.example.tanfeeth.Model.MyUser;
import com.example.tanfeeth.Model.Project;
import com.example.tanfeeth.Model.Staff;
import com.example.tanfeeth.Repository.*;
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
    private final StaffRepository staffRepository;

    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    public Set<Project> getCompanyProjects(Integer companyId) {
        MyUser user = myUserRepositroy.findMyUsersById(companyId);
        if (user == null)
            throw new ApiException("Company dosen't exist!");

        if (user.getInNeedCompany() == null) {
            changeStatusForProjectToDelayed();
            return user.getOperationCompany().getProjectSet();
        } else {
            changeStatusForProjectToDelayed();
            return user.getInNeedCompany().getProjectSet();
        }
    }



    public Project getProjectByCompanyId(Integer id, Integer projectId) {
        MyUser user = myUserRepositroy.findMyUsersById(id);
        Project p = projectRepository.findProjectById(projectId);
        if (p == null)
            throw new ApiException("Project dosen't exist!");
        //تحديد نوع الشركة
        if (!(user.getId() == p.getInNeedCompany().getId() || user.getId() == p.getOperationCompany().getId()))
            throw new ApiException("Project is not assigned to this company!");
        return p;
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

    public void finishProject(Integer idOC , Integer projectId){
        MyUser user = myUserRepositroy.findMyUsersById(idOC);
        Project project = projectRepository.findProjectById(projectId);
        project.setOperationCompany(user.getOperationCompany());
        List <Staff> staff=staffRepository.findStaffByProject(project);
        if(project == null)
            throw new ApiException("Project with this ID dosen't exist!");
        if(project.getOperationCompany().getId() != idOC)
            throw new ApiException("Project is not assigned to this company!");

        for(int i=0 ; i<staff.size();i++){
            staff.get(i).setStatus("FREE");
            staff.get(i).setProject(null);
            staffRepository.save(staff.get(i));
        }
        project.setStatus("FINISHED");
        projectRepository.save(project);
    }

    public void changeStatusForProjectToDelayed(){

        List<Project> project = projectRepository.findAll();
        for (int i =0 ; i<project.size();i++){
            System.out.println("---------------------------------------------");
            if (project.get(i).getEndDate().isBefore(LocalDateTime.now()) && !(project.get(i).getStatus().equalsIgnoreCase("FINISHED"))){
                System.out.println("---------------------------------------------");
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
