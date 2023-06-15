package com.example.tanfeeth.Service;

import com.example.tanfeeth.DTO.InNeedCompanyDTO;
import com.example.tanfeeth.DTO.OperationCompanyDTO;
import com.example.tanfeeth.Model.InNeedCompany;
import com.example.tanfeeth.Model.MyUser;
import com.example.tanfeeth.Model.OperationCompany;
import com.example.tanfeeth.Model.Project;
import com.example.tanfeeth.Repository.InNeedCompanyRepository;
import com.example.tanfeeth.Repository.MyUserRepositroy;
import com.example.tanfeeth.Repository.OperationCompanyRepository;
import com.example.tanfeeth.Repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyUserService {
    private final MyUserRepositroy myUserRepositroy;
    private final InNeedCompanyRepository inNeedCompanyRepository;
    private final OperationCompanyRepository operationCompanyRepository;
    private final ProjectRepository projectRepository;

    public List<MyUser> getAllUser(){
        return myUserRepositroy.findAll();
    }
    public void registerInNeedCompany(InNeedCompanyDTO inNeedCompanyDTO){
        String hash = new BCryptPasswordEncoder().encode(inNeedCompanyDTO.getPassword());
        MyUser userInNeedCompany = new MyUser(
                null,inNeedCompanyDTO.getEmail(),
                hash,
                inNeedCompanyDTO.getPhoneNumber(),
                "INNEED",
                null,
                null);
        InNeedCompany inNeedCompany = new InNeedCompany(
                userInNeedCompany.getId(),
                inNeedCompanyDTO.getName(),
                inNeedCompanyDTO.getCommerecePermit(),
                inNeedCompanyDTO.getWorkPermit(),
                userInNeedCompany,
                null,
                null);
        userInNeedCompany.setInNeedCompany(inNeedCompany);
        myUserRepositroy.save(userInNeedCompany);
        inNeedCompanyRepository.save(inNeedCompany);
    }
    public void registerOperationCompany(OperationCompanyDTO operationCompanyDTO){
        String hash = new BCryptPasswordEncoder().encode(operationCompanyDTO.getPassword());
        MyUser userOperationCompany = new MyUser(
                null,
                operationCompanyDTO.getEmail(),
                hash,
                operationCompanyDTO.getPhoneNumber(),
                "OPERATION",
                null,
                null
        );
        OperationCompany operationCompany = new OperationCompany(userOperationCompany.getId(),
                operationCompanyDTO.getName(),
                0.0,
                operationCompanyDTO.getWorkPermit(),
                operationCompanyDTO.getCommerecePermit(),
                operationCompanyDTO.getClassifacation(),
                operationCompanyDTO.getServiceList(),
                operationCompanyDTO.getField(),
                userOperationCompany,
                null,
                null,
                null);

        myUserRepositroy.save(userOperationCompany);
        operationCompanyRepository.save(operationCompany);

    }


    public void deleteUser(Integer id){
        MyUser user = myUserRepositroy.findMyUsersById(id);
        if (user.getInNeedCompany()==null){
            OperationCompany operationCompany = operationCompanyRepository.findOperationCompanyById(user.getId());
            List<Project> projectList = projectRepository.findProjectsByOperationCompany(operationCompany);
            for (int i= 0 ; i<projectList.size();i++){
                projectList.get(i).setOperationCompany(null);

            }
            operationCompanyRepository.delete(operationCompany);
            myUserRepositroy.delete(user);
        }else{
            InNeedCompany inNeedCompany = inNeedCompanyRepository.findInNeedCompanyById(user.getId());
            List<Project> projectList = projectRepository.findProjectsByInNeedCompany(inNeedCompany);
            for (int i= 0;i<projectList.size();i++){
                projectList.get(i).setInNeedCompany(null);
                projectRepository.delete(projectList.get(i));
            }
            inNeedCompanyRepository.delete(inNeedCompany);
            myUserRepositroy.delete(user);

        }
    }


}