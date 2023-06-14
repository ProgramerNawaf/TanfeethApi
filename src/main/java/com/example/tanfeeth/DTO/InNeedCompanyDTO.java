package com.example.tanfeeth.DTO;


import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class InNeedCompanyDTO {




    @NotNull(message = "Email can't be null!")
    @Email
    private String email;


    @NotNull(message = "Password can't be null!")
    private String password;


    @NotNull(message = "Phone number can't be null!")
    private String phoneNumber;


    @NotNull(message = "Name can't be null!")
    private String name;


    @NotNull(message = "Commerece permit can't be null!")
    private String commerecePermit;


    @NotNull(message = "Work permit can't be null!")
    private String workPermit;









}
