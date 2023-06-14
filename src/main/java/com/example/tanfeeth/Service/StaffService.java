package com.example.tanfeeth.Service;


import com.example.tanfeeth.ApiException.ApiException;
import com.example.tanfeeth.Model.MyUser;
import com.example.tanfeeth.Model.OperationCompany;
import com.example.tanfeeth.Model.Staff;
import com.example.tanfeeth.Repository.MyUserRepositroy;
import com.example.tanfeeth.Repository.OperationCompanyRepository;
import com.example.tanfeeth.Repository.ProjectRepository;
import com.example.tanfeeth.Repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StaffService {

    private final StaffRepository staffRepository;
    private final MyUserRepositroy myUserRepositroy;
    private final OperationCompanyRepository operationCompanyRepository;
    private final ProjectRepository projectRepository;
    public List<Staff> getAllStaffForCompany(Integer idOC){
       OperationCompany operationCompany = operationCompanyRepository.findOperationCompanyById(idOC);
       List<Staff> staffList = staffRepository.findStaffByOperationCompany(operationCompany);
       return staffList;
    }
    public void addStaff(Integer idOC,Staff staff){
        OperationCompany operationCompany = operationCompanyRepository.findOperationCompanyById(idOC);
        Staff staff1 = new Staff(null,
                staff.getName(),
                staff.getAge(),staff.getGender(),
                staff.getField(),
                staff.getNationality(),
                staff.getIdentifier(),
                staff.getStatus(),
                operationCompany,
                null);
        operationCompany.getStaffSet().add(staff1);
        staffRepository.save(staff1);

    }

    public void updateStaff(Integer idOC,Integer idStaff,Staff newStaff){
        MyUser user = myUserRepositroy.findMyUsersById(idOC);
        Staff staff = staffRepository.findStaffById(idStaff);
        if (staff == null || staff.getOperationCompany().getId()!= user.getId()){
            throw new ApiException("Staff with this ID dosen't exist!");
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
            throw new ApiException("Staff with this ID dosen't exist!");
        }
        staffRepository.delete(staff);
    }

    //ارجع العمال بأقامة منتهية
    public List<Staff> getStaffExipredIdentity(Integer idOC){
        MyUser operationCompany = myUserRepositroy.findMyUsersById(idOC);
        List<Staff> staff = staffRepository.findAll();
        if (staff.isEmpty()){
            throw new ApiException("No Staff Added!");
        }

        List<Staff> staffExpired = null ;
        for(int i = 0 ; i<staff.size();i++){
            if(staff.get(i).getIdentifier().isBefore(LocalDateTime.now()) && staff.get(i).getOperationCompany().getId() == operationCompany.getId()) {
                staff.get(i).setStatus("EXPIRED IDENTITY");
                staffRepository.save(staff.get(i));
                staffExpired.add(staff.get(i));
            }
        }

        return staffExpired;
    }

    public void assignStaffToProject(Integer idOC,Integer projectId,List <Integer> staffIds){
        MyUser operationCompany = myUserRepositroy.findMyUsersById(idOC);

        for(int i = 0 ; i<staffIds.size();i++) {
            Staff staff = staffRepository.findStaffById(staffIds.get(i));
            if (staff == null || staff.getOperationCompany().getId() != operationCompany.getId()) {
                throw new ApiException("Staff with this ID dosen't exist!");
            }
            projectRepository.findProjectById(projectId).getStaffs().add(staff);
            staffRepository.save(staff);
        }

    }



}
