package com.example.tanfeeth.Service;


import com.example.tanfeeth.ApiException.ApiException;
import com.example.tanfeeth.DTO.InNeedCompanyDTO;
import com.example.tanfeeth.Model.InNeedCompany;
import com.example.tanfeeth.Model.MyUser;
import com.example.tanfeeth.Model.OperationCompany;
import com.example.tanfeeth.Repository.InNeedCompanyRepository;
import com.example.tanfeeth.Repository.MyUserRepositroy;
import com.example.tanfeeth.Repository.OperationCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InNeedCompanyService {


    private final InNeedCompanyRepository inNeedCompanyRepository;
    private final MyUserRepositroy myUserRepositroy;
    private final OperationCompanyRepository operationCompanyRepository;


    public List<InNeedCompany> getAllCompany(){
        List<InNeedCompany> inNeedCompanies = inNeedCompanyRepository.findAll();
        return inNeedCompanies;
    }
    public MyUser getDetailsCompany(Integer idNC){
        MyUser user = myUserRepositroy.findMyUsersById(idNC);
        return user;
    }
    public void updateCompany(Integer idNC,InNeedCompanyDTO inNeedCompanyDTO){
        MyUser myUser = myUserRepositroy.findMyUsersById(idNC);
        InNeedCompany inNeedCompany = inNeedCompanyRepository.findInNeedCompanyById(idNC);
        myUser.setEmail(inNeedCompanyDTO.getEmail());
        myUser.setPhoneNumber(inNeedCompanyDTO.getPhoneNumber());
        String hash = new BCryptPasswordEncoder().encode(inNeedCompanyDTO.getPassword());
        myUser.setPassword(hash);
        inNeedCompany.setMyUser(myUser);
        inNeedCompany.setName(inNeedCompanyDTO.getName());
        inNeedCompany.setWorkPermit(inNeedCompanyDTO.getWorkPermit());
        inNeedCompany.setCommerecePermit(inNeedCompanyDTO.getCommerecePermit());
        myUserRepositroy.save(myUser);
        inNeedCompanyRepository.save(inNeedCompany);
    }

    public void rateOperationCompany(double rating , Integer idOc){
        if(rating>5 || rating <0)
            throw new ApiException("rating invalid!");
        MyUser user = myUserRepositroy.findMyUsersById(idOc);
        if(user ==null || user.getOperationCompany() == null)
            throw new ApiException("Operation company with this Id dosen't exist!");
        OperationCompany operationCompany = user.getOperationCompany();
        operationCompany.setNumOfRating(operationCompany.getNumOfRating()+1);
        operationCompany.setRate((operationCompany.getRate()+rating)/ operationCompany.getNumOfRating());
        operationCompanyRepository.save(operationCompany);
    }





}
