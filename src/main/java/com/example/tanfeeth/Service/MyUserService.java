package com.example.tanfeeth.Service;

import com.example.tanfeeth.DTO.RegisterInNeedCompanyDTO;
import com.example.tanfeeth.Model.InNeedCompany;
import com.example.tanfeeth.Model.MyUser;
import com.example.tanfeeth.Repository.InNeedCompanyRepository;
import com.example.tanfeeth.Repository.MyUserRepositroy;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyUserService {
    private final MyUserRepositroy MyUserRepositroy;
    private final InNeedCompanyRepository inNeedCompanyRepository;

    public List<MyUser> get(){
        return MyUserRepositroy.findAll();
    }
    // DTO register in need company
    public void registerInNeedCompany(RegisterInNeedCompanyDTO InNeedCompany){
        String hash = new BCryptPasswordEncoder().encode(InNeedCompany.getPassword());
        InNeedCompany.setPassword(hash);
        MyUser user = new MyUser(null, InNeedCompany.getEmail(), InNeedCompany.getPassword(), InNeedCompany.getPhoneNumber(),"INNEED", null,null);
        InNeedCompany inNeedCompany = new InNeedCompany(null, InNeedCompany.getName(), InNeedCompany.getCategory(), user,null,null);
        MyUserRepositroy.save(user);
        inNeedCompanyRepository.save(inNeedCompany);


    }
    // DTO register in operation
    public void OperationCompany(MyUser OperationCompany){
        String hash = new BCryptPasswordEncoder().encode(OperationCompany.getPassword());
        OperationCompany.setPassword(hash);
        OperationCompany.setRole("OPERATION");
        MyUserRepositroy.save(OperationCompany);
    }


}