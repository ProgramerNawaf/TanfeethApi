package com.example.tanfeeth.Service;

import com.example.tanfeeth.ApiException.ApiException;
import com.example.tanfeeth.DTO.OperationCompanyDTO;
import com.example.tanfeeth.Model.MyUser;
import com.example.tanfeeth.Model.OperationCompany;
import com.example.tanfeeth.Repository.MyUserRepositroy;
import com.example.tanfeeth.Repository.OperationCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.error.MarkedYAMLException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OperationCompanyService {


        private final OperationCompanyRepository operationCompanyRepository;
        private final MyUserRepositroy myUserRepositroy;


    public List<OperationCompany> getAllCompany() {
        return operationCompanyRepository.findAll();
    }
    public MyUser getDetailsCompany(Integer idOC) {
        MyUser user = myUserRepositroy.findMyUsersById(idOC);
        return user;
    }

    public void update(Integer id, OperationCompanyDTO operationCompanyDTO) {
        MyUser op = myUserRepositroy.findMyUsersById(id);
        OperationCompany oldoperationCompany = operationCompanyRepository.findOperationCompanyById(op.getId());
            if (oldoperationCompany == null) {
                throw new ApiException(" Operation Company Not found ");


            }
            op.setEmail(operationCompanyDTO.getEmail());
            op.setPhoneNumber(operationCompanyDTO.getPhoneNumber());
            String hash = new BCryptPasswordEncoder().encode(operationCompanyDTO.getPassword());
            op.setPassword(hash);
            oldoperationCompany.setName( operationCompanyDTO.getName());
            oldoperationCompany.setWorkPermit( operationCompanyDTO.getWorkPermit() );
            oldoperationCompany.setCommerecePermit( operationCompanyDTO.getCommerecePermit() );
            oldoperationCompany.setClassifacation( operationCompanyDTO.getClassifacation() );
            oldoperationCompany.setServiceList( operationCompanyDTO.getServiceList() );
            oldoperationCompany.setField( operationCompanyDTO.getField() );
            myUserRepositroy.save(op);
            operationCompanyRepository.save( oldoperationCompany );
        }

}
