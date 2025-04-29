package com.example.bankmanagementsystem.DTO;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class EmployeeDTO {
    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @NotEmpty
    private String name;

    @Email
    private String email;

    @NotEmpty
    private String position;

    @NotNull
    @PositiveOrZero
    private Double salary;
}
