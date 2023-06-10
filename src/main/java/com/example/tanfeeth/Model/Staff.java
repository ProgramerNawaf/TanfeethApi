package com.example.tanfeeth.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@AllArgsConstructor
@Setter
@Getter
@RequiredArgsConstructor
@Table(name = "staff")
public class Staff {
        @jakarta.persistence.Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer Id;
        private String name;
        private Integer age;
        private String field;
        private String nationality;
        private Date expiryDateIQAMA;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "operation_company_id",referencedColumnName = "my_user_id")
    private OperationCompany operationCompany;



    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "project_id",referencedColumnName = "id")
    private Project project;
}
