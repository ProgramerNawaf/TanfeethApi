package com.example.tanfeeth;


import com.example.tanfeeth.Model.Complaint;
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

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ComplaintRepositoryTest {

    @Autowired
    ComplaintRepository complaintRepository;
    @Autowired
    InNeedCompanyRepository inNeedCompanyRepository;
    @Autowired
    MyUserRepositroy myUserRepositroy;


    MyUser myUser;
    InNeedCompany inNeedCompany;
    Complaint complaint,complaint1;
    ArrayList<Complaint> complaints=new ArrayList<>();
    List<Complaint> complaintList = new ArrayList<>();
    @BeforeEach
    void setUp() {
        myUser = new MyUser(null,"m@gmail.com","12345","0530588577","INNEED",null,inNeedCompany);
        inNeedCompany = new InNeedCompany(null,"STC","1015000000","08329480298",myUser,null,null,null);
        complaint = new Complaint(null,"OcName","test",inNeedCompany);
        complaint1 = new Complaint(null,"OcName1","test1",inNeedCompany);
        complaints.add(complaint);
        complaints.add(complaint1);
    }

    @Test
    void findComplaintsByInNeedCompanyTest() {
       myUserRepositroy.save(myUser);
       inNeedCompanyRepository.save(inNeedCompany);
        complaintRepository.save(complaint);
       complaintRepository.save(complaint1);
        complaintList = complaintRepository.findComplaintsByInNeedCompany(inNeedCompany);
        Assertions.assertThat(complaintList.get(0)).isEqualTo(complaint);
    }
}
