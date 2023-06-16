package com.example.tanfeeth.Service;

import com.example.tanfeeth.ApiException.ApiException;
import com.example.tanfeeth.Model.Complaint;
import com.example.tanfeeth.Model.InNeedCompany;
import com.example.tanfeeth.Model.MyUser;
import com.example.tanfeeth.Model.OperationCompany;
import com.example.tanfeeth.Repository.ComplaintRepository;
import com.example.tanfeeth.Repository.InNeedCompanyRepository;
import com.example.tanfeeth.Repository.OperationCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final OperationCompanyRepository operationCompanyRepository;
    private final InNeedCompanyRepository inNeedCompanyRepository;


    public List<Complaint> getAll(){
        return complaintRepository.findAll();
    }

    public List<Complaint> getComplaints(MyUser user){
        List<Complaint> complaints = complaintRepository.findComplaintsByInNeedCompany(user.getInNeedCompany());
        return complaints ;
    }

    public void addComplaint(MyUser user, Complaint c){
        OperationCompany operationCompany = operationCompanyRepository.findOperationCompanyByName(c.getOcname());
        if(operationCompany == null)
            throw new ApiException("Operation company with this name dosen't exist!");
        InNeedCompany needCompany= user.getInNeedCompany();
        c.setInNeedCompany(needCompany);
        needCompany.getComplaintSet().add(c);
        complaintRepository.save(c);

    }


}
