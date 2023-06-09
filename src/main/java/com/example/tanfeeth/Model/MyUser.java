package com.example.tanfeeth.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Entity
@AllArgsConstructor
@Setter
@Getter
@RequiredArgsConstructor
public class MyUser {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    @NotNull(message = "username cant be null!")
    private String username;
    @NotNull(message = "password cant be null!")
    private String password;
    @Column(columnDefinition = "varchar(25) not null check (role='ADMIN' or role='OPERATION' role='INNEED')")
    @NotNull(message = "role cant be null!")
    private String role;
    @OneToOne(mappedBy = "operationUser",cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private OperationCompany operationCompany;
    @OneToOne(mappedBy = "inNeedUser",cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private InNeedCompany inNeedCompany;

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Collections.singleton(new SimpleGrantedAuthority(role));
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
}
