package com.example.tanfeeth;

import com.example.tanfeeth.Model.MyUser;
import com.example.tanfeeth.Repository.MyUserRepositroy;
import com.example.tanfeeth.Service.MyUserService;
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

public class MyUserServiceTest {

    @InjectMocks
    MyUserService myUserService;

    @Mock
    MyUserRepositroy myUserRepositroy;

    MyUser myUser,myUser1,myUser2;

    List<MyUser>myUserList=new ArrayList<>();

    @BeforeEach
    void setUp() {
        myUser = new MyUser(null,"mo@gmail.com","1221","053054950","INNEED",null,null);
        myUser1 = new MyUser(null,"mo@gmail.com","1221","053054950","INNEED",null,null);
        myUser2 = new MyUser(null,"mo@gmail.com","1221","053054950","INNEED",null,null);
        myUserList.add(myUser);
        myUserList.add(myUser1);
        myUserList.add(myUser2);
    }

    @Test
    void getAllUserTest() {
        when(myUserRepositroy.findAll()).thenReturn(myUserList);
        List<MyUser> myUsers = myUserService.getAllUser();
        Assertions.assertEquals(myUsers,myUserList);
        verify(myUserRepositroy,times(1)).findAll();
    }


}
