package com.example.bankmanagementsystem.Service;


import com.example.bankmanagementsystem.Api.ApiException;
import com.example.bankmanagementsystem.DTO.AccountDTO;
import com.example.bankmanagementsystem.Model.Account;
import com.example.bankmanagementsystem.Model.Customer;
import com.example.bankmanagementsystem.Repository.AccountRepository;
import com.example.bankmanagementsystem.Repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    // 2. Create a new bank account
    public void createAccount(Integer customerId, AccountDTO accountDTO) {
        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer == null) {
            throw new ApiException("Customer not found");
        }

        Account account = new Account();
        account.setAccountNumber(accountDTO.getAccountNumber());
        account.setBalance(accountDTO.getBalance());
        account.setIsActive(false);
        account.setCustomer(customer);

        accountRepository.save(account);
    }

    // 3. Active a bank account
    public void activateAccount(Integer customerId, Integer accountId) {
        Account account = checkAccount(customerId, accountId);

        account.setIsActive(true);
        accountRepository.save(account);
    }

    // View account details
    public Account getAccountDetails(Integer customerId, Integer accountId) {
        return checkAccount(customerId, accountId);
    }

    //    5. List user's account
    public List<Account> getAllAccounts(Integer customerId) {
        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer == null) {
            throw new ApiException("Customer not found");
        }
        return accountRepository.findAllByCustomer(customer);
    }

    //    6. Deposit (ايداع)
    public void deposit(Integer customerId, Integer accountId, Double amount) {
        if (amount <= 0) {
            throw new ApiException("Deposit amount must be greater than 0");
        }
        Account account = checkAccount(customerId, accountId);
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
    }

    //    6. withdraw (سحب)
    public void withdraw(Integer customerId, Integer accountId, Double amount) {
        if (amount <= 0) {
            throw new ApiException("Withdraw amount must be greater than 0");
        }
        Account account = checkAccount(customerId, accountId);
        if (account.getBalance() < amount) {
            throw new ApiException("not enough money!");
        }

        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);
    }

    // checkAccount
    private Account checkAccount(Integer customerId, Integer accountId) {
        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer == null) {
            throw new ApiException("Customer not found");
        }
        Account account = accountRepository.findAccountById(accountId);
        if (account == null) {
            throw new ApiException("Account not found");
        }

        if (!account.getCustomer().getId().equals(customer.getId())) {
            throw new ApiException("Not your account!");
        }
        return account;
    }

    //    7. Transfer between accounts
    public void transfer(Integer customerId, Integer fromAccountId, Integer toAccountId, Double amount) {
        if (amount <= 0) {
            throw new ApiException("Transfer amount must be greater than 0");
        }

        Account fromAccount = checkAccount(customerId, fromAccountId);
        Account toAccount = accountRepository.findAccountById(toAccountId);
        if (toAccount == null) {
            throw new ApiException("Account not found");
        }

        if (!fromAccount.getIsActive() || !toAccount.getIsActive()) {
            throw new ApiException("must be all active accounts!");
        }

        if (fromAccount.getBalance() < amount) {
            throw new ApiException("not enough money for transfer");
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }

    //    8. Block bank account
    public void blockAccount(Integer customerId, Integer accountId) {
        Account account = checkAccount(customerId, accountId);

        if (!account.getIsActive()) {
            throw new ApiException("Account is already blocked");
        }

        account.setIsActive(false);
        accountRepository.save(account);
    }


}
