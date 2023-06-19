package com.example.tanfeeth;

import com.example.tanfeeth.Model.Project;
import com.example.tanfeeth.Repository.ProjectRepository;
import com.example.tanfeeth.Service.ProjectService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {

    @InjectMocks
    ProjectService projectService;

    @Mock
    ProjectRepository projectRepository;

    Project project,project1,project2;

    List<Project> projectList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        project = new Project(null,"project","it","riyadh","test", LocalDateTime.now(),LocalDateTime.now(),"NEW",null,null,null,null,null);
        project1 = new Project(null,"project1","it","riyadh","test1",LocalDateTime.now(),LocalDateTime.now(),"NEW",null,null,null,null,null);
        project2 = new Project(null,"project2","it","riyadh","test2",LocalDateTime.now(),LocalDateTime.now(),"NEW",null,null,null,null,null);
        projectList.add(project);
        projectList.add(project1);
        projectList.add(project2);

    }

    @Test
    void getAllTest() {
        when(projectRepository.findAll()).thenReturn(projectList);
        List<Project> projects = projectService.getAll();
        Assertions.assertEquals(projects,projectList);
        verify(projectRepository,times(2)).findAll();
    }
}
