package com.example.tanfeeth.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
    private String name;
    // check type
    private Double rate;
    private String experience;
    private String workPermit;
    private String commerecePermit;
//    @Basic
//    private List<> serviceList;
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
