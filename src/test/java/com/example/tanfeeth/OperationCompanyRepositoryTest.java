package com.example.tanfeeth;


import com.example.tanfeeth.Model.MyUser;
import com.example.tanfeeth.Model.OperationCompany;
import com.example.tanfeeth.Repository.MyUserRepositroy;
import com.example.tanfeeth.Repository.OperationCompanyRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OperationCompanyRepositoryTest {

    @Autowired
    MyUserRepositroy myUserRepositroy;
    @Autowired
    OperationCompanyRepository operationCompanyRepository;

    MyUser myUser;
    OperationCompany operationCompany;
    List<String> strings = new ArrayList<>();

    @BeforeEach
    void setUp() {
        strings.add("test1");
        strings.add("test2");
        myUser = new MyUser(null,"m@gmail.com","329487","0530503434","OPERATION",true,null,null);
        operationCompany=new OperationCompany(null,"test",0.0,"138049283","3244234","test",strings,"it",myUser,null,null,null);
    }

    @Test
    void findOperationCompanyByIdTest() {
        myUserRepositroy.save(myUser);
        operationCompanyRepository.save(operationCompany);
        OperationCompany operationCompany1 = operationCompanyRepository.findOperationCompanyById(myUser.getId());
        Assertions.assertThat(operationCompany1).isEqualTo(operationCompany);
    }

    @Test
    void findOperationCompanyByNameTest() {
        myUserRepositroy.save(myUser);
        operationCompanyRepository.save(operationCompany);
        OperationCompany operationCompany1 = operationCompanyRepository.findOperationCompanyByName(operationCompany.getName());
        Assertions.assertThat(operationCompany1).isEqualTo(operationCompany);
    }
}
