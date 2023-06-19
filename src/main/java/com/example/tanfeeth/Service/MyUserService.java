package com.example.tanfeeth.Service;

import com.example.tanfeeth.DTO.InNeedCompanyDTO;
import com.example.tanfeeth.DTO.OperationCompanyDTO;
import com.example.tanfeeth.Model.*;
import com.example.tanfeeth.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyUserService {
    private final MyUserRepositroy myUserRepositroy;
    private final InNeedCompanyRepository inNeedCompanyRepository;
    private final OperationCompanyRepository operationCompanyRepository;
    private final ProjectRepository projectRepository;
    private final StaffRepository staffRepository;
    private final RequestRepository requestRepository;
    private final ReportRepository reportRepository;

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
                false,
                null,
                null);
        InNeedCompany inNeedCompany = new InNeedCompany(
                userInNeedCompany.getId(),
                inNeedCompanyDTO.getName(),
                inNeedCompanyDTO.getCommerecePermit(),
                inNeedCompanyDTO.getWorkPermit(),
                userInNeedCompany,
                null,
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
                false,
                null,
                null
        );
        OperationCompany operationCompany = new OperationCompany(userOperationCompany.getId(),
                operationCompanyDTO.getName(),
                0.0,
                0,
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
        List<Report> reportsProject=null;
        if (user.getInNeedCompany()==null){
            OperationCompany operationCompany = operationCompanyRepository.findOperationCompanyById(user.getId());
            List<Project> projectList = projectRepository.findProjectsByOperationCompany(operationCompany);
            List <Request> requestList = requestRepository.findRequestsByOperationCompany(operationCompany);
            List<Staff> staffProject=null;

            List<Staff> staffAll = staffRepository.findStaffByOperationCompany(operationCompany);
            for (int j = 0 ;j<staffAll.size();j++){
                staffAll.get(j).setProject(null);
                staffAll.get(j).setOperationCompany(null);
                staffRepository.delete(staffAll.get(j));
            }
            for (int i= 0 ; i<projectList.size();i++){
                projectList.get(i).setOperationCompany(null);
                projectList.get(i).setRequest(null);
                staffProject = staffRepository.findStaffByProject(projectList.get(i));

                for (int j = 0 ;j<staffProject.size();j++){
                    staffProject.get(j).setProject(null);
                    staffProject.get(j).setOperationCompany(null);
                    staffRepository.delete(staffProject.get(j));
                }
            }
            for(int i = 0 ; i<requestList.size();i++){
                requestList.get(i).setInNeedCompany(null);
                requestList.get(i).setOperationCompany(null);
                requestList.get(i).setProject(null);
                requestRepository.delete(requestList.get(i));
            }




            operationCompanyRepository.delete(operationCompany);
            myUserRepositroy.delete(user);
        }else{
            InNeedCompany inNeedCompany = inNeedCompanyRepository.findInNeedCompanyById(user.getId());
            List<Project> projectList = projectRepository.findProjectsByInNeedCompany(inNeedCompany);

            List<Staff> staffList =null;


            for (int i= 0;i<projectList.size();i++){
                projectList.get(i).setInNeedCompany(null);
                projectList.get(i).setOperationCompany(null);
                if(projectList.get(i).getRequest()!= null) {
                    projectList.get(i).getRequest().setProject(null);
                    projectList.get(i).setRequest(null);
                }
                reportsProject= reportRepository.findReportsByProject(projectList.get(i));
                for (int j = 0 ;j<reportsProject.size();j++){
                    reportsProject.get(j).setProject(null);
                    reportRepository.delete(reportsProject.get(j));
                }

                staffList = staffRepository.findStaffByProject(projectList.get(i));
                for (int j = 0 ;j<staffList.size();j++){
                    staffList.get(j).setProject(null);
                    staffList.get(j).setStatus("FREE");
                    staffRepository.save(staffList.get(j));
                }

                projectRepository.delete(projectList.get(i));
            }
            List <Request> requestList = requestRepository.findRequestsByInNeedCompany(inNeedCompany);
            for(int i = 0 ; i<requestList.size();i++){
                requestList.get(i).setInNeedCompany(null);
                requestList.get(i).setOperationCompany(null);
                requestList.get(i).setProject(null);
                requestRepository.delete(requestList.get(i));
            }

            inNeedCompanyRepository.delete(inNeedCompany);
            myUserRepositroy.delete(user);

        }
    }


}