package com.example.tanfeeth.Service;


import com.example.tanfeeth.DTO.InNeedCompanyDTO;
import com.example.tanfeeth.Model.InNeedCompany;
import com.example.tanfeeth.Model.MyUser;
import com.example.tanfeeth.Repository.MyUserRepositroy;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final MyUserRepositroy myUserRepositroy;
    private final MyUserService myUserService;

    public void register(MyUser user){
        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        MyUser admin = new MyUser(
                null,user.getEmail(),
                hash,
                user.getPhoneNumber(),
                "ADMIN",
                true,
                null,
                null);

        myUserRepositroy.save(admin);

    }

    public void handleCompany(boolean decision , Integer companyId){
        MyUser user =myUserRepositroy.findMyUsersById(companyId);
        if(decision){
        user.setEnabled(true);
        myUserRepositroy.save(user);}
        else{
           myUserService.deleteUser(user.getId());
        }

    }


}
