package com.example.tanfeeth.Service;


import com.example.tanfeeth.DTO.InNeedCompanyDTO;
import com.example.tanfeeth.Model.InNeedCompany;
import com.example.tanfeeth.Model.MyUser;
import com.example.tanfeeth.Repository.InNeedCompanyRepository;
import com.example.tanfeeth.Repository.MyUserRepositroy;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InNeedCompanyService {


    private final InNeedCompanyRepository inNeedCompanyRepository;
    private final MyUserRepositroy myUserRepositroy;




}
