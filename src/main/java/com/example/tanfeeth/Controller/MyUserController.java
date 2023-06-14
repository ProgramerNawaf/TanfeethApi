package com.example.tanfeeth.Controller;

import com.example.tanfeeth.DTO.InNeedCompanyDTO;
import com.example.tanfeeth.DTO.OperationCompanyDTO;
import com.example.tanfeeth.Model.MyUser;
import com.example.tanfeeth.Service.MyUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
@RestController
public class MyUserController {

    private final MyUserService myUserService;

    @PostMapping("/register/in-need-company")
    public ResponseEntity registerInNeedCompany(@Valid @RequestBody InNeedCompanyDTO inNeedCompany) {
        myUserService.registerInNeedCompany(inNeedCompany);
        return ResponseEntity.status(200).body("registered InNeed Company!");
    }
    @PostMapping("/register/operation-company")
    public ResponseEntity registerOperationCompany(@Valid @RequestBody OperationCompanyDTO operationCompany) {
        myUserService.registerOperationCompany(operationCompany);
        return ResponseEntity.status(200).body("registered Operation Company");
    }

    @GetMapping("/get")
    public List<MyUser> getAll() {
        return myUserService.get();
    }





}
