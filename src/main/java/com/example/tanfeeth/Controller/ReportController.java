package com.example.tanfeeth.Controller;


import com.example.tanfeeth.Model.MyUser;
import com.example.tanfeeth.Model.OperationCompany;
import com.example.tanfeeth.Model.Report;
import com.example.tanfeeth.Repository.ReportRepository;
import com.example.tanfeeth.Service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/report")
@RequiredArgsConstructor
@RestController
public class ReportController {
    private final ReportService reportService;


    @PostMapping("/add/{idProject}")
    public ResponseEntity addReport(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer idProject, @RequestBody Report report){
        reportService.addReport(myUser.getId(),idProject,report);
        return ResponseEntity.status(200).body("Report Added!");
    }

    @GetMapping("/get/{idProject}")
    public ResponseEntity getReportForProject(@AuthenticationPrincipal MyUser myUser , @PathVariable Integer idProject){
        List<Report> reportList = reportService.getReportForProject(myUser.getId(), idProject);
        return ResponseEntity.status(200).body(reportList);
    }


}
