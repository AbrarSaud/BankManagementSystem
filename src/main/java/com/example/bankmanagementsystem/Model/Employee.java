package com.example.bankmanagementsystem.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @NotEmpty(message = "The position must not be empty")
    @Column(columnDefinition = "varchar(150) not null")
    private String position;

    @NotNull(message = "The salary must not be null")
    @PositiveOrZero(message = "Salary must be a non-negative number")
    @Column(columnDefinition = "decimal(10,2) not null")
    private Double salary;

    @OneToOne
    @MapsId
    @JsonIgnore
    private MyUser user;
}
