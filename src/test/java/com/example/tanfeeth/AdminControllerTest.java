package com.example.tanfeeth;

import com.example.tanfeeth.ApiResponse.ApiResponse;
import com.example.tanfeeth.Controller.AdminController;
import com.example.tanfeeth.Model.*;
import com.example.tanfeeth.Service.*;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = AdminController.class , excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class AdminControllerTest {


    @MockBean
    MyUserService myUserService;
    @MockBean
    InNeedCompanyService inNeedCompanyService;
    @MockBean
    OperationCompanyService operationCompanyService ;
    @MockBean
    ProjectService projectService ;
    @MockBean
    RequestService requestService ;
    @MockBean
    StaffService staffService;
    @MockBean
    ComplaintService complaintService;
    @MockBean
    AdminService adminService ;



    @Autowired
    MockMvc mockMvc;
    MyUser myUser1,myUser2;
    InNeedCompany inNeedCompany;
    OperationCompany operationCompany;
    Project project;
    Request request;
    List<MyUser> myUserList ;
    List<InNeedCompany>inNeedCompanies;
    List<OperationCompany> operationCompanyList;
    List<Project> projectList;
    List<Request> requestList;
    ApiResponse apiResponse;

    @BeforeEach
    void setUp() {
        //get all user
        myUser1 = new MyUser(null,"i@gmail.com","2313","0530588577","INNEED",false,null,null);
        myUser2 = new MyUser(null,"o@gmail.com","2313","0530588577","INNEED",false,null,null);
        myUserList = Arrays.asList(myUser1);
        // get all InNeedC
        inNeedCompany = new InNeedCompany(null,"m","23234","234234",myUser1,null,null,null);
        inNeedCompanies = Arrays.asList(inNeedCompany);
        // get all OC
        operationCompany = new OperationCompany(null,"s",0.0,0,"2234","23423",null,null,null,myUser2,null,null,null);
        operationCompanyList = Arrays.asList(operationCompany);
        //get all project
        project = new Project(null,"stc",null,null,null,null,null,null,null,null,null,null,null);
        projectList = Arrays.asList(project);
        // get all request
        request = new Request(null,200.0,"new",null,null,null,null,null,null,null);
        requestList = Arrays.asList(request);
    }

    @Test
    void getAllUserTest() throws Exception {
        Mockito.when(myUserService.getAllUser()).thenReturn(myUserList);
        mockMvc.perform(get("/api/v1/admin/get-users"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("i@gmail.com"));
    }

    @Test
    void getAllInNeedCompanyTest() throws Exception {
        Mockito.when(inNeedCompanyService.getAllCompany()).thenReturn(inNeedCompanies);
        mockMvc.perform(get("/api/v1/admin/get-all-inneed"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("m"));
    }

    @Test
    void getAllOperationCompanyTest() throws Exception {
        Mockito.when(operationCompanyService.getAllCompany()).thenReturn(operationCompanyList);
        mockMvc.perform(get("/api/v1/admin/get-all-operation"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("s"));
    }

    @Test
    void getAllProjectsTest() throws Exception {
        Mockito.when(projectService.getAll()).thenReturn(projectList);
        mockMvc.perform(get("/api/v1/admin/get-all-project"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("stc"));
    }

    @Test
    void getAllRequestTest() throws Exception {
        Mockito.when(requestService.getAll()).thenReturn(requestList);
        mockMvc.perform(get("/api/v1/admin/get-all-request"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].offer").value(200.0));
    }
}
