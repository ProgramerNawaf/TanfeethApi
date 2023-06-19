package com.example.tanfeeth.Service;

import com.example.tanfeeth.ApiException.ApiException;

import com.example.tanfeeth.DTO.RequestDTO;
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


    public List<Request> getAll() {
        List<Request> requests = requestRepository.findAll();
        return requests;
    }

    public List<Request> getCompanyRequests(Integer idCompany){
        MyUser user = myUserRepositroy.findMyUsersById(idCompany);
        if(user.getInNeedCompany() == null)
          return requestRepository.findRequestsByOperationCompany(user.getOperationCompany());
        else
            return requestRepository.findRequestsByInNeedCompany(user.getInNeedCompany());
    }

    public void addRequest(Integer idCompany, RequestDTO request){
        MyUser user = myUserRepositroy.findMyUsersById(idCompany);
        Project project = projectRepository.findProjectById(request.getProjectId());
        if(project == null)
            throw new ApiException("Project with this ID dosen't exist!");
        Request newRequest = new Request();

        if(user.getOperationCompany() == null){
            newRequest.setInNeedCompany(user.getInNeedCompany());
            MyUser operationUser =myUserRepositroy.findMyUsersById(request.getOperationCompanyId());
            if(operationUser.getOperationCompany() == null)
                throw new ApiException("Operation company with this ID dosen't exist!");
            newRequest.setOperationCompany(operationUser.getOperationCompany());
            newRequest.setOffer(request.getOffer());
            newRequest.setProjectId(request.getProjectId());
            newRequest.setStatus("INPROGRESS");
            newRequest.setCreatedAt(LocalDateTime.now());
            newRequest.setCreatedBy(idCompany);
            newRequest.setUpdatedBy(idCompany);
            user.getInNeedCompany().getRequestSet().add(newRequest);
            requestRepository.save(newRequest);
        }else{
            newRequest.setOperationCompany(user.getOperationCompany());
            newRequest.setInNeedCompany(project.getInNeedCompany());
            newRequest.setStatus("INPROGRESS");
            newRequest.setCreatedAt(LocalDateTime.now());
            newRequest.setOffer(request.getOffer());
            newRequest.setProjectId(request.getProjectId());
            newRequest.setCreatedBy(idCompany);
            newRequest.setUpdatedBy(idCompany);
            user.getOperationCompany().getRequestSet().add(newRequest);
            requestRepository.save(newRequest);
        }
    }

    public void updateRequest(Integer companyId ,Request r , Integer requestId){
        MyUser user = myUserRepositroy.findMyUsersById(companyId);
        Request request = requestRepository.findRequestById(requestId);
        if(request==null)
            throw new ApiException("Request with this id dosen't exist!");

        boolean flag=false;
        if(user.getInNeedCompany() == null){
            List<Request> requestList = requestRepository.findRequestsByOperationCompany(user.getOperationCompany());

            for(int i = 0 ; i<requestList.size();i++) {
                if (request.getId() == requestList.get(i).getId())
                    flag = true;
            }
            if(!flag)
                throw new ApiException("No such request for this company!");
            request.setOffer(r.getOffer());
            request.setStatus(r.getStatus());
            request.setUpdatedBy(request.getOperationCompany().getId());
            requestRepository.save(request);
        }else{
            List<Request> requestList = requestRepository.findRequestsByInNeedCompany(user.getInNeedCompany());
            for(int i = 0 ; i<requestList.size();i++) {
                if (request.getId() == requestList.get(i).getId())
                    flag = true;
            }
            if(!flag)
                throw new ApiException("No such request for this company!");

            if(!(r.getStatus() == null)) {
                if (r.getStatus().equalsIgnoreCase("APPROVED")) {
                    Project project = projectRepository.findProjectById(request.getProjectId());
                    request.setProject(project);
                    project.setRequest(request);

                    project.setOperationCompany(myUserRepositroy.findMyUsersById(request.getUpdatedBy()).getOperationCompany());

                    projectRepository.save(project);
                }

                if (r.getStatus().equalsIgnoreCase("DECLINED")) {
                    request.setInNeedCompany(null);
                    request.setOperationCompany(null);
                    request.setProject(null);
                    requestRepository.delete(request);
                    return;
                }
            }

            if (r.getOffer()!=null){
                request.setOffer(r.getOffer());
            }
            if(!(r.getStatus() == null)) {
                request.setStatus(r.getStatus());
            }
            request.setUpdatedBy(request.getInNeedCompany().getId());
            requestRepository.save(request);

        }
    }


}
