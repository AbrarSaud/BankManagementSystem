package com.example.bankmanagementsystem.Service;


import com.example.bankmanagementsystem.DTO.CustomerDTO;
import com.example.bankmanagementsystem.DTO.EmployeeDTO;
import com.example.bankmanagementsystem.Model.Customer;
import com.example.bankmanagementsystem.Model.Employee;
import com.example.bankmanagementsystem.Model.MyUser;
import com.example.bankmanagementsystem.Repository.AuthRepository;
import com.example.bankmanagementsystem.Repository.CustomerRepository;
import com.example.bankmanagementsystem.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;

   //  register Customer
    public void registerCustomer(CustomerDTO customerDTO) {
        MyUser user = new MyUser();
        user.setUsername(customerDTO.getUsername());
        user.setRole("CUSTOMER");
        user.setPassword(new BCryptPasswordEncoder().encode(customerDTO.getPassword()));
        user.setName(customerDTO.getName());
        user.setEmail(customerDTO.getEmail());
        authRepository.save(user);

        Customer customer = new Customer(null,customerDTO.getPhoneNumber(),user,null);
        customerRepository.save(customer);
    }

    // register Employee
    public void registerEmployee(EmployeeDTO employeeDTO) {
        MyUser user = new MyUser();
        user.setUsername(employeeDTO.getUsername());
        user.setRole("EMPLOYEE");
        user.setPassword(new BCryptPasswordEncoder().encode(employeeDTO.getPassword()));
        user.setName(employeeDTO.getName());
        user.setEmail(employeeDTO.getEmail());
        authRepository.save(user);

        Employee employee = new Employee();
        employee.setUser(user);
        employee.setPosition(employeeDTO.getPosition());
        employee.setSalary(employeeDTO.getSalary());
        employeeRepository.save(employee);
    }



}
