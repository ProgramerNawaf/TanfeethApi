package com.example.tanfeeth.Service;

import com.example.tanfeeth.ApiException.ApiException;
import com.example.tanfeeth.Model.MyUser;
import com.example.tanfeeth.Model.Project;
import com.example.tanfeeth.Repository.InNeedCompanyRepository;
import com.example.tanfeeth.Repository.MyUserRepositroy;
import com.example.tanfeeth.Repository.OperationCompanyRepository;
import com.example.tanfeeth.Repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class projectService {

    private final ProjectRepository projectRepository;
    private final MyUserRepositroy myUserRepositroy;
    private final OperationCompanyRepository operationCompanyRepository;
    private final InNeedCompanyRepository inNeedCompanyRepository;

    public List<Project> getAll(){
        return projectRepository.findAll();
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
        if(user == null)
            new ApiException("Company dosen't exist!");
        //تحديد نوع الشركة
           user.getOperationCompany().getProjectSet().add(p);
           operationCompanyRepository.save(user.getOperationCompany());

    }
    public void deleteProject(Project p , Integer id){
        Project project =projectRepository.findProjectById(id);
        if(project == null)
            new ApiException("Project dosen't exist!");

            project.setOperationCompany(null);
            projectRepository.save(project);
    }


}
