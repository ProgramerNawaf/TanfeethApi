package com.example.tanfeeth.Controller;


import com.example.tanfeeth.DTO.InNeedCompanyDTO;
import com.example.tanfeeth.Model.InNeedCompany;
import com.example.tanfeeth.Model.MyUser;
import com.example.tanfeeth.Repository.InNeedCompanyRepository;
import com.example.tanfeeth.Service.InNeedCompanyService;
import com.example.tanfeeth.Service.MyUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/inneed-company")
@RequiredArgsConstructor
@RestController
public class InNeedCompanyController {

    private final MyUserService myUserService;
    private final InNeedCompanyService inNeedCompanyService;



    @GetMapping("/get")
    public ResponseEntity getAllInNeedCompany(){
        List<InNeedCompany> inNeedCompanies = inNeedCompanyService.getAllCompany();
        return ResponseEntity.status(200).body(inNeedCompanies);
    }
    @GetMapping("/get-details")
    public ResponseEntity getCompany(@AuthenticationPrincipal MyUser myUser){
        MyUser user = inNeedCompanyService.getDetailsCompany(myUser.getId());
        return ResponseEntity.status(200).body(user);
    }

    @PutMapping("/update")
    public ResponseEntity updateCompany(@AuthenticationPrincipal MyUser myUser, @RequestBody InNeedCompanyDTO inNeedCompanyDTO){
        inNeedCompanyService.updateCompany(myUser.getId(),inNeedCompanyDTO);
        return ResponseEntity.status(200).body("done updated ");

    }
    @PostMapping("/logout")
    public ResponseEntity logout() {
        return ResponseEntity.status(200).body("logout user!");
    }
}



