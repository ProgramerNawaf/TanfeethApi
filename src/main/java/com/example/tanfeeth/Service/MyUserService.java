package com.example.tanfeeth.Service;

import com.example.tanfeeth.DTO.InNeedCompanyDTO;
import com.example.tanfeeth.DTO.OperationCompanyDTO;
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
public class MyUserService {
    private final MyUserRepositroy myUserRepositroy;
    private final InNeedCompanyRepository inNeedCompanyRepository;
    private final OperationCompanyRepository operationCompanyRepository;

    public List<MyUser> get(){
        return myUserRepositroy.findAll();
    }
    // DTO register in need company
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
        myUserRepositroy.delete(user);

    }


}