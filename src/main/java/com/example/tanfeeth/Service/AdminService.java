package com.example.tanfeeth.Service;


import com.example.tanfeeth.Config.AuthenticationResponse;
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
    private final MyUserDetailsService myUserDetailsService;

    public AuthenticationResponse register(MyUser user){
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
        var jwtToken = myUserDetailsService.generateJwtToken(admin);
//                var refreshToken = myUserDetailsService.generateRefreshToken(userInNeedCompany);
        return new AuthenticationResponse(jwtToken);
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
