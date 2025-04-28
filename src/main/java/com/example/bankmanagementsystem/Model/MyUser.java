package com.example.bankmanagementsystem.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class MyUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "The user name must not be empty")
    @Column(columnDefinition = "varchar(10) not null unique")
    @Size(min = 4, max = 10)
    private String username;

    @Column(columnDefinition = "varchar(150) not null")
    @NotEmpty
    @Size(min = 6)
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).{6,}$", message = "Password must contain at least one letter and one number")
    private String password;

    @Column(columnDefinition = "varchar(20) not null")
    @NotEmpty
    @Size(min = 2, max = 20)
    private String name;

    @Column(columnDefinition = "varchar(150) not null unique")
    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Pattern(regexp = "CUSTOMER|EMPLOYEE|ADMIN", message = "Role must be CUSTOMER, EMPLOYEE, or ADMIN")
    private String role;

    @OneToOne(cascade = CascadeType.ALL , mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL , mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Employee employee;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
