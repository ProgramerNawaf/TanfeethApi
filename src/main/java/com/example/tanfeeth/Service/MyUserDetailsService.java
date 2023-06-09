package com.example.tanfeeth.Service;


import com.example.tanfeeth.Model.MyUser;
import com.example.tanfeeth.Repository.MyUserRepositroy;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//@Service
//@RequiredArgsConstructor
//public class MyUserDetailsService implements UserDetailsService {
//
//
//    private final MyUserRepositroy myUserRepositroy;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        MyUser user=myUserRepositroy.findMyUsersByUsername(username);
//
//        if(user==null){
//            throw new UsernameNotFoundException("Wrong username or password");
//        }
//
//        return user;
//    }
//}