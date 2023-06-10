package com.example.tanfeeth.DTO;


import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RegisterInNeedCompanyDTO {

    private String username;
    private String name;
    private String category;
    @NotNull(message = "password cant be null!")
    private String password;
    private String email;
    private String phoneNumber;
    @Column(columnDefinition = "varchar(25) not null check (role='ADMIN' or role='OPERATION' or role='INNEED')")
//    @NotNull(message = "role cant be null!")
    private String role;




}
