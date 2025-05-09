package com.example.bankmanagementsystem.Repository;

import com.example.bankmanagementsystem.Model.Account;
import com.example.bankmanagementsystem.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account , Integer> {
    Account findAccountById(Integer id);
    List<Account> findAllByCustomer(Customer customer);

}
