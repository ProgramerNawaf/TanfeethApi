package com.example.tanfeeth.Service;


import com.example.tanfeeth.Model.MyUser;
import com.example.tanfeeth.Repository.InNeedCompanyRepository;
import com.example.tanfeeth.Repository.MyUserRepositroy;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InNeedCompanyService {


    private final InNeedCompanyRepository inNeedCompanyRepository;
    private final MyUserRepositroy myUserRepositroy;


    // param --> DTO have all details
    public void register(MyUser usr){
        String hash = new BCryptPasswordEncoder().encode(usr.getPassword());
        usr.setPassword(hash);
//        usr.setRole("CUSTOMER");
        myUserRepositroy.save(usr);
//        InNeedCompany inNeedCompany = new InNeedCompany(null,usr,null,null)
//       inNeedCompanyRepository.save();
    }


}
