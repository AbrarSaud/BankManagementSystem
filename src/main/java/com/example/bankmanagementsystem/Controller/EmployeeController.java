package com.example.bankmanagementsystem.Controller;


import com.example.bankmanagementsystem.Api.ApiResponse;
import com.example.bankmanagementsystem.DTO.EmployeeDTO;
import com.example.bankmanagementsystem.Model.MyUser;
import com.example.bankmanagementsystem.Service.AuthService;
import com.example.bankmanagementsystem.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bank_system/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final AuthService authService;
    private final EmployeeService employeeService;


    @PostMapping("/register")
    public ResponseEntity<?> registerEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        authService.registerEmployee(employeeDTO);
        return ResponseEntity.ok(new ApiResponse("Employee registered successfully!"));
    }

    // Get All Employees
    @GetMapping("/get")
    public ResponseEntity<?> getAllEmployees(@AuthenticationPrincipal MyUser user) {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    // Update Employee
    @PutMapping("/update")
    public ResponseEntity<?> updateEmployee(@AuthenticationPrincipal MyUser user, @Valid @RequestBody EmployeeDTO employeeDTO) {
        employeeService.updateEmployee(user.getId(), employeeDTO);
        return ResponseEntity.ok(new ApiResponse("Employee updated !"));
    }

    // Delete Employee
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteEmployee(@AuthenticationPrincipal MyUser user) {
        employeeService.deleteEmployee(user.getId());
        return ResponseEntity.ok(new ApiResponse("Employee deleted !"));
    }
}
