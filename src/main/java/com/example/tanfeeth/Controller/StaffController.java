package com.example.tanfeeth.Controller;


import com.example.tanfeeth.Model.MyUser;
import com.example.tanfeeth.Model.Staff;
import com.example.tanfeeth.Service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/v1/staff")
@RequiredArgsConstructor
@RestController
public class StaffController {
    private final StaffService staffService;

    @GetMapping("/get")
    public ResponseEntity getAllStaffForCompany(@AuthenticationPrincipal MyUser user){
        List<Staff> staffList =staffService.getAllStaffForCompany(user.getId());
        return ResponseEntity.status(200).body(staffList);
    }
    @PostMapping("/add")
    public ResponseEntity addStaff(@AuthenticationPrincipal MyUser user,@RequestBody Staff staff){
        staffService.addStaff(user.getId(),staff);
        return ResponseEntity.status(200).body("Staff Added!");
    }

    @PutMapping("/update-staff/{idStaff}")
    public ResponseEntity updateStaff(@AuthenticationPrincipal MyUser user,@PathVariable Integer idStaff,@RequestBody Staff staff){
        staffService.updateStaff(user.getId(),idStaff,staff);
        return ResponseEntity.status(200).body("Staff updated!");
    }

    @DeleteMapping("/delete/{idStaff}")
    public ResponseEntity deleteStaff(@AuthenticationPrincipal MyUser user,@PathVariable Integer idStaff){
        staffService.deleteStaff(user.getId(), idStaff);
        return ResponseEntity.status(200).body("Staff deleted!");
    }

    @GetMapping("/getExpired")
    public ResponseEntity getExpiredStaff(@AuthenticationPrincipal MyUser user){

        return ResponseEntity.status(200).body(staffService.getStaffExipredIdentity(user.getId()));
    }

    @GetMapping("/assignProject/{idOC}/{projectId}")
    public ResponseEntity getExpiredStaff(@AuthenticationPrincipal MyUser user, @RequestBody List <Integer> staffIds , @PathVariable Integer idOC, @PathVariable Integer projectId){
        staffService.assignStaffToProject(idOC,projectId,staffIds);
        return ResponseEntity.status(200).body("Staff assigned!");
    }







}
