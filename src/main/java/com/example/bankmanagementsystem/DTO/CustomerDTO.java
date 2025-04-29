package com.example.bankmanagementsystem.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class CustomerDTO {
    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @NotEmpty
    private String name;

    @Email
    private String email;

    @NotEmpty
    @Pattern(regexp = "^05\\d{8}$", message = "Phone number must start with 05")
    private String phoneNumber;
}
