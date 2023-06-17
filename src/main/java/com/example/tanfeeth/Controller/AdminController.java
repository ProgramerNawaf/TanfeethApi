package com.example.tanfeeth.Controller;


import com.example.tanfeeth.Model.*;
import com.example.tanfeeth.Repository.MyUserRepositroy;
import com.example.tanfeeth.Service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@RestController
public class AdminController {


    private final MyUserService myUserService;
    private final InNeedCompanyService inNeedCompanyService;
    private final OperationCompanyService operationCompanyService;
    private final ProjectService projectService;
    private final RequestService requestService;
    private final StaffService staffService;
    private final ComplaintService complaintService;
    private final AdminService adminService;

    @GetMapping("/get-users")
    public ResponseEntity getAllUser() {
        List<MyUser> myUsers = myUserService.getAllUser();
        return ResponseEntity.status(200).body(myUsers);
    }
    @GetMapping("/get-all-inneed")
    public ResponseEntity getAllInNeedCompany(){
        List<InNeedCompany> inNeedCompanies = inNeedCompanyService.getAllCompany();
        return ResponseEntity.status(200).body(inNeedCompanies);
    }

    @GetMapping("/get-all-operation")
    public ResponseEntity getAllOperationCompany(){
        List<OperationCompany> operationCompanyList = operationCompanyService.getAllCompany();
        return ResponseEntity.status(200).body(operationCompanyList);
    }

    @GetMapping("/get-all-project")
    public ResponseEntity getAllProjects(){
        List<Project> projectList = projectService.getAll();
        return ResponseEntity.status(200).body(projectList);
    }
    @GetMapping("/get-all-request")
    public ResponseEntity getAllRequest(){
        List<Request> requests = requestService.getAll();
        return ResponseEntity.status(200).body(requests);
    }

    @GetMapping("/get-all-staff")
    public ResponseEntity getAllStaff(){
        List<Staff> staffList = staffService.getAllStaffForAllCompany();
        return ResponseEntity.status(200).body(staffList);
    }

    @GetMapping("/get-all-complaint")
    public ResponseEntity getAllComplaint(){
        return ResponseEntity.status(200).body(complaintService.getAll());
    }


    @PutMapping("/activate/{companyId}")
    public ResponseEntity activate(@PathVariable Integer companyId){
        adminService.handleCompany(true,companyId);

        return ResponseEntity.status(200).body("Company Activated!");
    }

    @PutMapping("/deactivate/{companyId}")
    public ResponseEntity deactivate(@PathVariable Integer companyId){
        adminService.handleCompany(false,companyId);

        return ResponseEntity.status(200).body("Company Deactivated!");
    }

    @PostMapping("/register")
    public ResponseEntity registerAdmin(@RequestBody MyUser admin){
        adminService.register(admin);
        return ResponseEntity.status(200).body("Admin added!");
    }





}