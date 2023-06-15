package com.example.tanfeeth.Controller;


import com.example.tanfeeth.Model.MyUser;
import com.example.tanfeeth.Model.Project;
import com.example.tanfeeth.Repository.ProjectRepository;
import com.example.tanfeeth.Service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/project")
@RequiredArgsConstructor
@RestController
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping("/get")
    public ResponseEntity getAll(){
        List<Project> projectList = projectService.getAll();
        return ResponseEntity.status(200).body(projectList);
    }

    @GetMapping("/get-project-company")
    public ResponseEntity getCompanyProjects(@AuthenticationPrincipal MyUser user){

        return ResponseEntity.status(200).body(projectService.getCompanyProject(user.getId()));
    }

    @PostMapping("/add")
    public ResponseEntity addProject(@AuthenticationPrincipal MyUser user,@RequestBody Project project){
        projectService.addProject(project,user.getId());
        return ResponseEntity.status(200).body("Project Added!");
    }

    @DeleteMapping("/delete/{projectId}")
    public ResponseEntity deleteProject(@AuthenticationPrincipal MyUser user,@PathVariable Integer projectId){
        projectService.deleteProject(user.getId(),projectId);
        return ResponseEntity.status(200).body("Project Deleted!");
    }


}
