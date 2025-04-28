package com.example.bankmanagementsystem.Service;

import com.example.bankmanagementsystem.Api.ApiException;
import com.example.bankmanagementsystem.DTO.EmployeeDTO;
import com.example.bankmanagementsystem.Model.Customer;
import com.example.bankmanagementsystem.Model.Employee;
import com.example.bankmanagementsystem.Model.MyUser;
import com.example.bankmanagementsystem.Repository.AuthRepository;
import com.example.bankmanagementsystem.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final AuthRepository authRepository;

    // Get All Employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Update Employee
    public void updateEmployee(Integer id, EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.findEmployeeById(id);

        MyUser user = employee.getUser();
        user.setUsername(employeeDTO.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(employeeDTO.getPassword()));
        user.setName(employeeDTO.getName());
        user.setEmail(employeeDTO.getEmail());

        employee.setPosition(employeeDTO.getPosition());
        employee.setSalary(employeeDTO.getSalary());

        authRepository.save(user);
        employeeRepository.save(employee);
    }

    // Delete Employee
    public void deleteEmployee(Integer id) {
        Employee employee = employeeRepository.findEmployeeById(id);
        if (employee == null) {
            throw new ApiException("Customer not found");
        }

        employeeRepository.delete(employee);
        authRepository.delete(employee.getUser());

    }

}
