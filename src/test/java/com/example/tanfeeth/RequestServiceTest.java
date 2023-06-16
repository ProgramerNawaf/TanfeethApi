package com.example.tanfeeth;


import com.example.tanfeeth.Model.Request;
import com.example.tanfeeth.Repository.RequestRepository;
import com.example.tanfeeth.Service.RequestService;
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

public class RequestServiceTest {

    @InjectMocks
    RequestService requestService;
    @Mock
    RequestRepository requestRepository;

    Request request,request1,request2;

    List<Request> requestList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        request = new Request(null,20.0,"new",null,null,null,null,null,null,null);
        request1 = new Request(null,200.0,"new",null,null,null,null,null,null,null);
        request2 = new Request(null,203.0,"new",null,null,null,null,null,null,null);

        requestList.add(request1);
        requestList.add(request);
        requestList.add(request2);

    }

    @Test
    void getAllTest() {

        when(requestRepository.findAll()).thenReturn(requestList);
        List<Request> requests = requestService.getAll();
        Assertions.assertEquals(requests,requestList);
        verify(requestRepository,times(1)).findAll();
    }
}
