package com.example.tanfeeth;


import com.example.tanfeeth.Model.Staff;
import com.example.tanfeeth.Repository.StaffRepository;
import com.example.tanfeeth.Service.StaffService;
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

public class StaffServiceTest {

    @InjectMocks
    StaffService staffService;
    @Mock
    StaffRepository staffRepository;

    Staff staff,staff1,staff2;
    List<Staff> staffList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        staff = new Staff(null,"mohammed1",23,"M","it","saudi", LocalDateTime.now(),null,null,null);
        staff1 = new Staff(null,"mohammed2",23,"M","it","saudi",LocalDateTime.now(),null,null,null);
        staff2 = new Staff(null,"mohammed3",23,"M","it","saudi",LocalDateTime.now(),null,null,null);
        staffList.add(staff);
        staffList.add(staff1);
        staffList.add(staff2);
    }

    @Test
    void getAllStaffForAllCompanyTest() {
        when(staffRepository.findAll()).thenReturn(staffList);
        List<Staff> staffList1 =staffService.getAllStaffForAllCompany();
        Assertions.assertEquals(staffList1,staffList);
        verify(staffRepository,times(2)).findAll();
    }

}
