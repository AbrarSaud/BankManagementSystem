package com.example.bankmanagementsystem.Controller;

import com.example.bankmanagementsystem.Api.ApiResponse;
import com.example.bankmanagementsystem.DTO.CustomerDTO;
import com.example.bankmanagementsystem.Model.Customer;
import com.example.bankmanagementsystem.Model.MyUser;
import com.example.bankmanagementsystem.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/bank_system/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;


    @PostMapping("/register")
    public ResponseEntity<?> registerCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        customerService.registerCustomer(customerDTO);
        return ResponseEntity.ok(new ApiResponse("Customer registered !"));
    }

    // Get All Customer
    @GetMapping("/get")
    public ResponseEntity<?> getAllCustomers(@AuthenticationPrincipal Customer customer) {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    // Update Customer
    @PutMapping("/update")
    public ResponseEntity<?> updateCustomer(@AuthenticationPrincipal  MyUser user, @Valid @RequestBody CustomerDTO customerDTO) {
        customerService.updateCustomer(user.getId(), customerDTO);
        return ResponseEntity.ok(new ApiResponse("Customer updated !"));
    }

    // Delete Customer
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteCustomer(@AuthenticationPrincipal Customer customer) {
        customerService.deleteCustomer(customer.getId());
        return ResponseEntity.ok(new ApiResponse("Customer deleted !"));
    }

}
