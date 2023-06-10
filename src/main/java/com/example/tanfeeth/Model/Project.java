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
public class Project {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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
}
