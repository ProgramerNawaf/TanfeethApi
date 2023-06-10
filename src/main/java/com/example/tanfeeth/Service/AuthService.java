package com.example.tanfeeth.Service;

import com.example.tanfeeth.Model.MyUser;
import com.example.tanfeeth.Repository.MyUserRepositroy;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MyUserRepositroy MyUserRepositroy;

    public List<MyUser> get(){
        return MyUserRepositroy.findAll();
    }
    public void register(MyUser usr){
        String hash = new BCryptPasswordEncoder().encode(usr.getPassword());
        usr.setPassword(hash);
//        usr.setRole("CUSTOMER");
        MyUserRepositroy.save(usr);
    }
}