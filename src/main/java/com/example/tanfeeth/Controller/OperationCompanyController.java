package com.example.tanfeeth.Controller;


import com.example.tanfeeth.DTO.OperationCompanyDTO;
import com.example.tanfeeth.Model.MyUser;
import com.example.tanfeeth.Model.OperationCompany;
import com.example.tanfeeth.Model.Staff;
import com.example.tanfeeth.Service.OperationCompanyService;
import com.example.tanfeeth.Service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/opreation-comany")
@RequiredArgsConstructor
@RestController
public class OperationCompanyController {

    private final OperationCompanyService operationCompanyService;

    @GetMapping("/get")
    public ResponseEntity getAllOperationCompany(){
        List<OperationCompany> operationCompanyList = operationCompanyService.getAllCompany();
        return ResponseEntity.status(200).body(operationCompanyList);
    }
    @GetMapping("/get-details")
    public ResponseEntity getCompany(@AuthenticationPrincipal MyUser myUser){
        MyUser user = operationCompanyService.getDetailsCompany(myUser.getId());
        return ResponseEntity.status(200).body(user);
    }

    @PutMapping("/update")
    public ResponseEntity update(@AuthenticationPrincipal MyUser myUser, @RequestBody OperationCompanyDTO OperationCompanyDTO){
        operationCompanyService.update(myUser.getId(),OperationCompanyDTO);
        return ResponseEntity.status(200).body( "operation company updated" );
    }




}
