package com.example.tanfeeth.DTO;


import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class InNeedCompanyDTO {

    private Integer id;
    private String email;
    private String password;
    private String phoneNumber;
    private String name;
    private String commerecePermit;
    private String workPermit;









}
