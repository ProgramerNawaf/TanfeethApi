package com.example.tanfeeth.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@Setter
@Getter
@RequiredArgsConstructor

public class OperationCompany {
    @Id
    private Integer id;
    @Column(columnDefinition = "varchar(20) not null")
    @NotNull(message = "name can't be null!")
    private String name;
    // check type

    private Double rate;
    @Column(columnDefinition = "varchar(20) not null")
    @NotNull(message = "Work permit can't be null!")
    private String workPermit;
    @Column(columnDefinition = "varchar(20) not null")
    @NotNull(message = "Commerece permit can't be null!")
    private String commerecePermit;
    @Column(columnDefinition = "varchar(20) not null")
    @NotNull(message = "Classifacation can't be null!")
    private String classifacation;
//    @Basic
    @ElementCollection
    @NotNull(message = "Service's can't be null!")
    private List<String> serviceList;

    @Column(columnDefinition = "varchar(20) not null")
    @NotNull(message = "Field can't be null!")
    private String field;










    @OneToOne
    @MapsId
    @JsonIgnore
    private MyUser myUser;



    @OneToMany(mappedBy = "operationCompany",cascade = CascadeType.DETACH )
    @PrimaryKeyJoinColumn
    private Set<Request> requestSet;

    @OneToMany(mappedBy = "operationCompany",cascade = CascadeType.DETACH )
    @PrimaryKeyJoinColumn
    private Set<Project> projectSet;


    @OneToMany(mappedBy = "operationCompany",cascade = CascadeType.DETACH )
    @PrimaryKeyJoinColumn
    private Set<Staff> staffSet;
}
