package com.example.tanfeeth.Controller;


import com.example.tanfeeth.DTO.RequestDTO;
import com.example.tanfeeth.Model.MyUser;
import com.example.tanfeeth.Model.Request;
import com.example.tanfeeth.Service.RequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/request")
@RequiredArgsConstructor
@RestController
public class RequestController {

    private final RequestService requestService;

    @GetMapping("/get")
    public ResponseEntity getAllRequest(){
        List<Request> requests = requestService.getAll();
        return ResponseEntity.status(200).body(requests);
    }


    @GetMapping("/get-for-company")
    public ResponseEntity getAllRequestsForCompany(@AuthenticationPrincipal MyUser user){

        return ResponseEntity.status(200).body(requestService.getCompanyRequests(user.getId()));
    }

    @PostMapping("/add")
    public ResponseEntity addRequest(@AuthenticationPrincipal MyUser user,@Valid @RequestBody RequestDTO request){
        requestService.addRequest(user.getId(), request);
        return ResponseEntity.status(200).body("Request send!");
    }

    @PutMapping("/update/{requestID}")
    public ResponseEntity addRequest(@AuthenticationPrincipal MyUser user,@Valid @RequestBody Request request,@PathVariable Integer requestID){
        requestService.updateRequest(user.getId(), request,requestID);
        return ResponseEntity.status(200).body("Request updated!");
    }
}
