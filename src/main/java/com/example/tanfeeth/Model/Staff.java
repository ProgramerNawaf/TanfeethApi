package com.example.tanfeeth.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
@AllArgsConstructor
@Setter
@Getter
@RequiredArgsConstructor
public class Staff {
        @jakarta.persistence.Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer Id;

        @Column(columnDefinition = "varchar(20) not null")
        @NotNull(message = "Name can't be null!")
        private String name;

        @Column(columnDefinition = "int not null")
        @NotNull(message = "Age can't be null!")
        private Integer age;

        @Column(columnDefinition = "varchar(20) not null")
        @NotNull(message = "Gender can't be null!")
        private String gender;

        @Column(columnDefinition = "varchar(20) not null")
        @NotNull(message = "Field can't be null!")
        private String field;

        @Column(columnDefinition = "varchar(20) not null")
        @NotNull(message = "Nationality can't be null!")
        private String nationality;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
//        @NotNull(message = "Identifier date can't be null!")
        private LocalDateTime identifier;

        @Column(columnDefinition = "varchar(25) not null check (status='FREE' or status='WORKING' or status='VACATION' or status = 'EXPIRED IDENTITY')")
        @NotNull(message = "Status can't be null!")
        private String status;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "operation_company_id",referencedColumnName = "my_user_id")
    private OperationCompany operationCompany;



    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "project_id",referencedColumnName = "id")
    private Project project;
}
