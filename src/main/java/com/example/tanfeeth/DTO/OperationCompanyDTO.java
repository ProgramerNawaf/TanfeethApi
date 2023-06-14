package com.example.tanfeeth.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class OperationCompanyDTO {


    private Integer id;
    private String email;
    private String password;
    private String phoneNumber;
    private String name;
    private String experience;
    private String workPermit;
    private String commerecePermit;
    //    @Basic
    private List<String> serviceList;
    private String field;




}
