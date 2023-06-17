package com.example.tanfeeth;

import com.example.tanfeeth.Model.InNeedCompany;
import com.example.tanfeeth.Model.MyUser;
import com.example.tanfeeth.Repository.ComplaintRepository;
import com.example.tanfeeth.Repository.InNeedCompanyRepository;
import com.example.tanfeeth.Repository.MyUserRepositroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class InNeedCompanyRepositoryTest {

    @Autowired
    InNeedCompanyRepository inNeedCompanyRepository;


    @Autowired
    MyUserRepositroy myUserRepositroy;


    MyUser myUser;
    InNeedCompany inNeedCompany;

    @BeforeEach
    void setUp() {
        myUser = new MyUser(null,"m@gmail.com","12345","0530588577","INNEED",true,null,inNeedCompany);
        inNeedCompany = new InNeedCompany(null,"STC","1015000000","08329480298",myUser,null,null,null);
    }

    @Test
    void findInNeedCompanyById() {
        myUserRepositroy.save(myUser);
        inNeedCompanyRepository.save(inNeedCompany);
        InNeedCompany inNeedCompany1 = inNeedCompanyRepository.findInNeedCompanyById(inNeedCompany.getId());
        Assertions.assertThat(inNeedCompany1).isEqualTo(inNeedCompany);

    }
}
