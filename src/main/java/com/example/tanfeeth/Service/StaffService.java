package com.example.tanfeeth.Service;


import com.example.tanfeeth.ApiException.ApiException;
import com.example.tanfeeth.Model.*;
import com.example.tanfeeth.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private final RequestRepository requestRepository;


    public List<Staff> getAllStaffForAllCompany(){
        changeStaffToExpiredIdentity();
        List<Staff> staffList = staffRepository.findAll();
        return staffList;
    }

    public List<Staff> getAllStaffForCompany(Integer idOC) {
        OperationCompany operationCompany = operationCompanyRepository.findOperationCompanyById(idOC);
        changeStaffToExpiredIdentity();
        List<Staff> staffList = staffRepository.findStaffByOperationCompany(operationCompany);
        return staffList;
    }

    public void addStaff(Integer idOC, Staff staff) {
        OperationCompany operationCompany = operationCompanyRepository.findOperationCompanyById(idOC);
        staff.setOperationCompany(operationCompany);
        operationCompany.getStaffSet().add(staff);
        staffRepository.save(staff);
    }

    public void updateStaff(Integer idOC, Integer idStaff, Staff newStaff) {
        MyUser user = myUserRepositroy.findMyUsersById(idOC);
        Staff staff = staffRepository.findStaffById(idStaff);
        if (staff == null || staff.getOperationCompany().getId() != user.getId()) {
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

    public void deleteStaff(Integer idOC, Integer idStaff) {
        MyUser user = myUserRepositroy.findMyUsersById(idOC);
        Staff staff = staffRepository.findStaffById(idStaff);
        if (staff == null || staff.getOperationCompany().getId() != user.getId()) {
            throw new ApiException("Staff with this ID dosen't exist!");
        }
        staffRepository.delete(staff);
    }

    //ارجع العمال بأقامة منتهية
    public void changeStaffToExpiredIdentity() {

        List<Staff> staff = staffRepository.findAll();
        if (staff.isEmpty()) {
            throw new ApiException("No Staff Added!");
        }


        for (int i = 0; i < staff.size(); i++) {
            if (staff.get(i).getIdentifier().isBefore(LocalDateTime.now()) ) {
                staff.get(i).setStatus("EXPIRED IDENTITY");
                staffRepository.save(staff.get(i));
            }
        }

    }
    public void assignStaffToProject(Integer idOC, Integer requestId, List<Integer> staffIds) {
        MyUser operationCompany = myUserRepositroy.findMyUsersById(idOC);
        Request request = requestRepository.findRequestById(requestId);
        if (request == null)
            throw new ApiException("Project with this ID dosen't exist!");
        Project project = request.getProject();

        for (int i = 0; i < staffIds.size(); i++) {
            Staff staff = staffRepository.findStaffById(staffIds.get(i));
            if (staff == null || staff.getOperationCompany().getId() != operationCompany.getId()) {
                throw new ApiException("Staff with this ID dosen't exist!");
            }

            staff.setProject(project);
            staff.setStatus("WORKING");
            project.getStaffs().add(staff);
            staffRepository.save(staff);
        }

    }

    public void changeStatusVacation(Integer idOC,Integer staffId) {
        Staff staff = staffRepository.findStaffById(staffId);
        if (staff == null && staff.getOperationCompany().getId()==idOC)
            throw new ApiException("Staff with this ID dosen't exist!");
        if (!(staff.getStatus().equalsIgnoreCase("FREE")))
            throw new ApiException("Staff is not free!");
        staff.setStatus("VACATION");
        staffRepository.save(staff);
    }

    public List<Staff> getAllStaffExpired(Integer idOC){
        OperationCompany operationCompany = operationCompanyRepository.findOperationCompanyById(idOC);
        List<Staff> staffList = staffRepository.findStaffByOperationCompany(operationCompany);
        List<Staff> expiredStaff = new ArrayList<>();
        for (int i = 0 ; i<staffList.size();i++){
            if (staffList.get(i).getStatus().equalsIgnoreCase("EXPIRED IDENTITY")){
                expiredStaff.add(staffList.get(i));
            }
        }
        return expiredStaff;


    }



}
