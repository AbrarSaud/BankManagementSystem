package com.example.bankmanagementsystem.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @NotEmpty(message = "Account number must not be empty")
    @Pattern(regexp = "^\\d{4}-\\d{4}-\\d{4}-\\d{4}$", message = "Account number must follow the format XXXX-XXXX-XXXX-XXXX")
    @Column(columnDefinition = "varchar(19) not null")
    private String accountNumber;

    @NotNull(message = "Balance must not be null")
    @PositiveOrZero(message = "Balance must be a not negative decimal number")
    private Double balance;

    @Column(columnDefinition = "boolean default false")
    private Boolean isActive = false;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}