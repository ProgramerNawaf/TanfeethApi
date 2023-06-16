package com.example.tanfeeth.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@AllArgsConstructor
@Setter
@Getter
@RequiredArgsConstructor

public class InNeedCompany {

    @Id
    private Integer id;
    @Column(columnDefinition = "varchar(20) not null")
    @NotNull(message = "Name can't be null!")
    private String name;

    @Column(columnDefinition = "varchar(20) not null")
    @NotNull(message = "Commerece permit can't be null!")
    private String commerecePermit;

    @Column(columnDefinition = "varchar(20) not null")
    @NotNull(message = "Work permit can't be null!")
    private String workPermit;






    @OneToOne
    @MapsId
    @JsonIgnore
    private MyUser myUser;

    @OneToMany(mappedBy = "inNeedCompany",cascade = CascadeType.DETACH )
    @PrimaryKeyJoinColumn
    private Set<Request> requestSet;

    @OneToMany(mappedBy = "inNeedCompany",cascade = CascadeType.DETACH )
    @PrimaryKeyJoinColumn
    private Set<Complaint> complaintSet;


    @OneToMany(mappedBy = "inNeedCompany",cascade = CascadeType.DETACH )
    @PrimaryKeyJoinColumn
    private Set<Project> projectSet ;
}
