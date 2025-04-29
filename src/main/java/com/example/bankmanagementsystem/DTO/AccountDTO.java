package com.example.bankmanagementsystem.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountDTO {

    @NotEmpty(message = "Account number must not be empty")
    @Pattern(regexp = "^\\d{4}-\\d{4}-\\d{4}-\\d{4}$", message = "Account number must follow the format XXXX-XXXX-XXXX-XXXX")
    private String accountNumber;

    @NotNull(message = "Balance must not be null")
    @PositiveOrZero(message = "Balance must be a non negative decimal number")
    private Double balance;

}
