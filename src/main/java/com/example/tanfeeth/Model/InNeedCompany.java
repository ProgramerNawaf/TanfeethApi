package com.example.tanfeeth.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
@Table(name = "in_need_company")
public class InNeedCompany {

    @Id
    private Integer id;
    private String name;
    private String category;




    @OneToOne
    @MapsId
    @JsonIgnore
    private MyUser myUser;

    @OneToMany(mappedBy = "inNeedCompany",cascade = CascadeType.DETACH )
    @PrimaryKeyJoinColumn
    private Set<Request> requestSet;


    @OneToMany(mappedBy = "inNeedCompany",cascade = CascadeType.DETACH )
    @PrimaryKeyJoinColumn
    private Set<Project> projectSet ;
}
