package com.example.tanfeeth.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class OperationCompanyDTO {



    @NotNull(message = "Email can't be null!")
    @Email
    private String email;


    @NotNull(message = "Password can't be null!")
    private String password;


    @NotNull(message = "Phone number can't be null!")
    private String phoneNumber;

    @NotNull(message = "Name can't be null!")
    private String name;

    @NotNull(message = "Work Permit can't be null!")
    private String workPermit;

    @NotNull(message = "Commerece Permit can't be null!")
    private String commerecePermit;
    //    @Basic

    @NotNull(message = "Classifacation can't be null!")
    private String classifacation;

    @NotNull(message = "Services can't be null!")
    private List<String> serviceList;

    @NotNull(message = "Field can't be null!")
    private String field;




}
