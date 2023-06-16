package com.example.tanfeeth.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Setter
@Getter
@RequiredArgsConstructor
public class Complaint {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "Operation company name cant be null!")
    private String ocname;
    @Column(columnDefinition = "varchar(50) not null")
    @NotNull(message = "Message cant be null!")
    private String message;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "in_need_company_id",referencedColumnName = "my_user_id")
    private InNeedCompany inNeedCompany;

}
