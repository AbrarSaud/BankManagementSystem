package com.example.bankmanagementsystem.Service;

import com.example.bankmanagementsystem.Api.ApiException;
import com.example.bankmanagementsystem.DTO.CustomerDTO;
import com.example.bankmanagementsystem.Model.Customer;
import com.example.bankmanagementsystem.Model.MyUser;
import com.example.bankmanagementsystem.Repository.AuthRepository;
import com.example.bankmanagementsystem.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {


    private final CustomerRepository customerRepository;
    private final AuthRepository authRepository;

    // get All Customers
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

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

    //  Update Customer
    public void updateCustomer(Integer id, CustomerDTO customerDTO) {
        Customer customer = customerRepository.findCustomerById(id);
        if (customer == null) {
            throw new RuntimeException("Customer not found ");
        }
        MyUser user = customer.getUser();
        user.setUsername(customerDTO.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(customerDTO.getPassword()));
        user.setName(customerDTO.getName());
        user.setEmail(customerDTO.getEmail());

        customer.setPhoneNumber(customerDTO.getPhoneNumber());

        authRepository.save(user);
        customerRepository.save(customer);
    }

    //  delete Customer
    public void deleteCustomer(Integer id) {
        Customer customer = customerRepository.findCustomerById(id);
        if (customer == null) {
            throw new ApiException("Customer not found");
        }

        customerRepository.delete(customer);
        authRepository.delete(customer.getUser());
    }
}
