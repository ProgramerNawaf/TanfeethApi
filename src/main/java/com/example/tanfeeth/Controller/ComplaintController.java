package com.example.tanfeeth.Controller;

import com.example.tanfeeth.Model.Complaint;
import com.example.tanfeeth.Model.MyUser;
import com.example.tanfeeth.Model.Staff;
import com.example.tanfeeth.Service.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/api/v1/complaint")
@RequiredArgsConstructor
@RestController
public class ComplaintController {

    private final ComplaintService complaintService;

    @GetMapping("/getAll")
    public ResponseEntity getAll(@AuthenticationPrincipal MyUser user){

        return ResponseEntity.status(200).body(complaintService.getAll());
    }
    @GetMapping("/get")
    public ResponseEntity getComplaintForCompany(@AuthenticationPrincipal MyUser user){

        return ResponseEntity.status(200).body(complaintService.getComplaints(user));
    }
    @PostMapping("/add")
    public ResponseEntity addComplaint(@AuthenticationPrincipal MyUser user,@RequestBody Complaint complaint){
        complaintService.addComplaint(user,complaint);
        return ResponseEntity.status(200).body("Complaint Added!");
    }
}
