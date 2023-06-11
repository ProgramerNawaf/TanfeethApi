package com.example.tanfeeth.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@AllArgsConstructor
@Setter
@Getter
@RequiredArgsConstructor
@Table(name = "request")
public class Request {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private Double offer;
    private String status;
    private Date dateReceived;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "operation_company_id",referencedColumnName = "my_user_id")
    private OperationCompany operationCompany;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "in_need_company_id",referencedColumnName = "my_user_id")
    private InNeedCompany inNeedCompany;
}
