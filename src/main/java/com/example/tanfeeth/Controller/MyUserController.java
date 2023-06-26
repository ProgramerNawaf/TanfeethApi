package com.example.tanfeeth.Controller;

import com.example.tanfeeth.Config.AuthenticationResponse;
import com.example.tanfeeth.DTO.InNeedCompanyDTO;
import com.example.tanfeeth.DTO.OperationCompanyDTO;
import com.example.tanfeeth.Model.MyUser;
import com.example.tanfeeth.Service.MyUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
@RestController
public class MyUserController {

    private final MyUserService myUserService;

    @PostMapping("/register/in-need-company")
    public ResponseEntity<AuthenticationResponse> registerInNeedCompany(@Valid @RequestBody InNeedCompanyDTO inNeedCompany) {

        return ResponseEntity.status(200).body(myUserService.registerInNeedCompany(inNeedCompany));
    }
    @PostMapping("/register/operation-company")
    public ResponseEntity<AuthenticationResponse> registerOperationCompany(@Valid @RequestBody OperationCompanyDTO operationCompany) {

        return ResponseEntity.status(200).body(myUserService.registerOperationCompany(operationCompany));
    }


    @DeleteMapping("/delete")
    public ResponseEntity delete(@AuthenticationPrincipal MyUser user){
        myUserService.deleteUser(user.getId());
        return ResponseEntity.status(200).body("deleted User!");
    }

    @GetMapping("/logout")
    public ResponseEntity logout(){
        return ResponseEntity.status(200).body("logout");
    }








}
