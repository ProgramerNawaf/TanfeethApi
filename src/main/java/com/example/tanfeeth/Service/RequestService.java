package com.example.tanfeeth.Service;

import com.example.tanfeeth.Model.MyUser;
import com.example.tanfeeth.Model.Request;
import com.example.tanfeeth.Repository.MyUserRepositroy;
import com.example.tanfeeth.Repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor

public class RequestService {

    private final RequestRepository requestRepository;
    private final MyUserRepositroy myUserRepositroy;

    public List<Request> getCompanyRequests(Integer idCompany){
        MyUser user = myUserRepositroy.findMyUsersById(idCompany);
        if(user.getInNeedCompany() == null)
          return requestRepository.findRequestsByOperationCompany(user.getOperationCompany());
        else
            return requestRepository.findRequestsByInNeedCompany(user.getInNeedCompany());
    }

    public void addRequest(Integer idCompany,Request request){
        MyUser user = myUserRepositroy.findMyUsersById(idCompany);

        if(user.getOperationCompany() == null){
            request.setInNeedCompany(user.getInNeedCompany());
            user.getInNeedCompany().getRequestSet().add(request);
            requestRepository.save(request);
        }else{
            request.setOperationCompany(user.getOperationCompany());
            user.getOperationCompany().getRequestSet().add(request);
            requestRepository.save(request);
        }
    }
}
