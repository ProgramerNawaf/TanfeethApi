package com.example.tanfeeth.Service;

import com.example.tanfeeth.ApiException.ApiException;

import com.example.tanfeeth.Model.MyUser;
import com.example.tanfeeth.Model.Project;
import com.example.tanfeeth.Model.Request;
import com.example.tanfeeth.Repository.MyUserRepositroy;
import com.example.tanfeeth.Repository.ProjectRepository;
import com.example.tanfeeth.Repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor

public class RequestService {

    private final RequestRepository requestRepository;
    private final MyUserRepositroy myUserRepositroy;
    private final ProjectRepository projectRepository;

    public List<Request> getCompanyRequests(Integer idCompany){
        MyUser user = myUserRepositroy.findMyUsersById(idCompany);
        if(user.getInNeedCompany() == null)
          return requestRepository.findRequestsByOperationCompany(user.getOperationCompany());
        else
            return requestRepository.findRequestsByInNeedCompany(user.getInNeedCompany());
    }

    public void addRequest(Integer idCompany, Request request){
        MyUser user = myUserRepositroy.findMyUsersById(idCompany);
        Project project = projectRepository.findProjectByName(request.getProjectName());
        if(project == null)
            throw new ApiException("Project with this ID dosen't exist!");

        if(user.getOperationCompany() == null){
            request.setInNeedCompany(user.getInNeedCompany());
            request.setStatus("INPROGRESS");
            request.setCreatedAt(LocalDateTime.now());
            request.setCreatedBy(idCompany);
            request.setUpdatedBy(idCompany);
            user.getInNeedCompany().getRequestSet().add(request);
            requestRepository.save(request);
        }else{
            request.setOperationCompany(user.getOperationCompany());
            request.setStatus("INPROGRESS");
            request.setCreatedAt(LocalDateTime.now());
            request.setCreatedBy(idCompany);
            request.setUpdatedBy(idCompany);
            user.getOperationCompany().getRequestSet().add(request);
            requestRepository.save(request);
        }
    }


}
