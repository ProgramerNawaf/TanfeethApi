package com.example.tanfeeth.Controller;


import com.example.tanfeeth.Model.MyUser;
import com.example.tanfeeth.Service.MyUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/inneed-company")
@RequiredArgsConstructor
@RestController
public class InNeedCompanyController {

    private final MyUserService myUserService;

    @GetMapping("/get")
    public List<MyUser> getAll() {
        return myUserService.get();
    }

//    @PostMapping("/register")
//    public ResponseEntity myUser(@Valid @RequestBody MyUser user) {
//        myUserService.registerInNeedCompany(user);
//        return ResponseEntity.status(200).body("registered User!");
//    }

//    @PostMapping("/admin")
//    public ResponseEntity admin() {
//        return ResponseEntity.status(200).body("welcome admin!");
//    }


//    @PostMapping("/login")
//    public ResponseEntity login() {
//        return ResponseEntity.status(200).body("login user!");
//    }

    @PostMapping("/logout")
    public ResponseEntity logout() {
        return ResponseEntity.status(200).body("logout user!");
    }
}



