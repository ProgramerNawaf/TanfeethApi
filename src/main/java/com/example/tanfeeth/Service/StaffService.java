package com.example.tanfeeth.Service;


import com.example.tanfeeth.ApiException.ApiException;
import com.example.tanfeeth.Model.MyUser;
import com.example.tanfeeth.Model.OperationCompany;
import com.example.tanfeeth.Model.Staff;
import com.example.tanfeeth.Repository.MyUserRepositroy;
import com.example.tanfeeth.Repository.OperationCompanyRepository;
import com.example.tanfeeth.Repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StaffService {

    private final StaffRepository staffRepository;
    private final MyUserRepositroy myUserRepositroy;
    private final OperationCompanyRepository operationCompanyRepository;
    public Set<Staff> getAllStaffForCompany(Integer idOC){
       MyUser user = myUserRepositroy.findMyUsersById(idOC);
       return user.getOperationCompany().getStaffSet();
    }
    public void addStaff(Integer idOC,Staff staff){
        MyUser user = myUserRepositroy.findMyUsersById(idOC);
        user.getOperationCompany().getStaffSet().add(staff);
        operationCompanyRepository.save(user.getOperationCompany());
        staffRepository.save(staff);
    }

    public void updateStaff(Integer idOC,Integer idStaff,Staff newStaff){
        MyUser user = myUserRepositroy.findMyUsersById(idOC);
        Staff staff = staffRepository.findStaffById(idStaff);
        if (staff == null || staff.getOperationCompany().getId()!= user.getId()){
            throw new ApiException("do not have any staff for this id");
        }
        staff.setAge(newStaff.getAge());
        staff.setName(newStaff.getName());
        staff.setField(newStaff.getField());
        staff.setGender(newStaff.getGender());
        staff.setIdentifier(newStaff.getIdentifier());
        staff.setStatus(newStaff.getStatus());
        staff.setNationality(newStaff.getNationality());
        staffRepository.save(staff);
    }

    public void deleteStaff(Integer idOC,Integer idStaff){
        MyUser user = myUserRepositroy.findMyUsersById(idOC);
        Staff staff = staffRepository.findStaffById(idStaff);
        if (staff == null || staff.getOperationCompany().getId()!= user.getId()){
            throw new ApiException("do not have any staff for this id");
        }
        staff.setOperationCompany(null);
        user.getOperationCompany().getStaffSet().remove(staff);
        operationCompanyRepository.save(user.getOperationCompany());
        staffRepository.save(staff);
        staffRepository.delete(staff);
    }



}
