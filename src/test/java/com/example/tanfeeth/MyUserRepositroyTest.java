package com.example.tanfeeth;

import com.example.tanfeeth.Model.MyUser;
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
public class MyUserRepositroyTest {

    @Autowired
    MyUserRepositroy myUserRepositroy;

    MyUser myUser;


    @BeforeEach
    void setUp() {
        myUser = new MyUser(null,"m@gmail.com","1234","053059845","INNEED",null,null);

    }

    @Test
    void findMyUsersByIdTest() {
        myUserRepositroy.save(myUser);
        MyUser myUser1 = myUserRepositroy.findMyUsersById(myUser.getId());
        Assertions.assertThat(myUser1).isEqualTo(myUser);
    }

    @Test
    void findMyUserByEmailTest() {
        myUserRepositroy.save(myUser);
        MyUser myUser1 = myUserRepositroy.findMyUserByEmail(myUser.getEmail());
        Assertions.assertThat(myUser1).isEqualTo(myUser);
    }
}
