package com.example.tanfeeth;

import com.example.tanfeeth.Model.Complaint;
import com.example.tanfeeth.Repository.ComplaintRepository;
import com.example.tanfeeth.Service.ComplaintService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class ComplaintServiceTest {

    @InjectMocks
    ComplaintService complaintService;
    @Mock
    ComplaintRepository complaintRepository;

    Complaint complaint1,complaint2,complaint3;
    List<Complaint> complaints = new ArrayList<>();

    @BeforeEach
    void setUp() {
        complaint1 = new Complaint(null,"OC","test",null);
        complaint2 = new Complaint(null,"OC1","test1",null);
        complaint3 = new Complaint(null,"OC12","test1",null);

        complaints.add(complaint1);
        complaints.add(complaint2);
        complaints.add(complaint3);

    }

    @Test
    void getAllTest() {
        when(complaintRepository.findAll()).thenReturn(complaints);
        List<Complaint>complaintList = complaintService.getAll();
        Assertions.assertEquals(complaintList,complaints);
        verify(complaintRepository,times(1)).findAll();
    }
}
