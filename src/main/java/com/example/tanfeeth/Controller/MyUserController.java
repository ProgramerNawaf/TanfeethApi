package com.example.tanfeeth.Controller;

import com.example.tanfeeth.DTO.RegisterInNeedCompanyDTO;
import com.example.tanfeeth.Model.MyUser;
import com.example.tanfeeth.Service.MyUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
@RestController
public class MyUserController {

    private final MyUserService myUserService;

    @PostMapping("/register/in-need-company")
    public ResponseEntity registerInNeedCompany(@Valid @RequestBody RegisterInNeedCompanyDTO inNeedCompany) {
        myUserService.registerInNeedCompany(inNeedCompany);
        return ResponseEntity.status(200).body("registered InNeed company!");
    }
    @PostMapping("/register/operation-company")
    public ResponseEntity OperationCompany(@Valid @RequestBody MyUser operationCompany) {
        myUserService.OperationCompany(operationCompany);
        return ResponseEntity.status(200).body("registered OperationCompany");
    }





}
