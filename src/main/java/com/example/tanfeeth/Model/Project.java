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

public class Project {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(20) not null")
    @NotNull(message = "Name can't be null!")
    private String name;

    @Column(columnDefinition = "varchar(20) not null")
    @NotNull(message = "Category can't be null!")
    private String category;

    @Column(columnDefinition = "varchar(20) not null")
    @NotNull(message = "Location can't be null!")
    private String location;

    @Column(columnDefinition = "varchar(100) not null")
    @NotNull(message = "Description can't be null!")
    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endDate;

    @Column(columnDefinition = "varchar(20) not null check (status='NEW' or status='ONGOING' or status='DELAYED' or status='FINISHED')")
    private String status;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "operation_company_id",referencedColumnName = "my_user_id")
    private OperationCompany operationCompany;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "in_need_company_id",referencedColumnName = "my_user_id")
    private InNeedCompany inNeedCompany;


    @OneToMany(mappedBy = "project",cascade = CascadeType.DETACH )
    @PrimaryKeyJoinColumn
    private Set<Staff> staffs;

    @OneToOne
    @JsonIgnore
    private Request request;
}
