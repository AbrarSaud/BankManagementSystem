package com.example.bankmanagementsystem.Controller;

import com.example.bankmanagementsystem.Api.ApiResponse;
import com.example.bankmanagementsystem.DTO.AccountDTO;
import com.example.bankmanagementsystem.Model.Customer;
import com.example.bankmanagementsystem.Service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bank_system/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    // 2. Create a new bank account
    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@AuthenticationPrincipal Customer customer, @Valid @RequestBody AccountDTO accountDTO) {
        accountService.createAccount(customer.getId(), accountDTO);
        return ResponseEntity.ok(new ApiResponse("Account created !"));
    }

    // 3. Active a bank account
    @PutMapping("/activate/{accountId}")
    public ResponseEntity<?> activateAccount(@AuthenticationPrincipal Customer customer, @PathVariable Integer accountId) {
        accountService.activateAccount(customer.getId(), accountId);
        return ResponseEntity.ok(new ApiResponse("Account activated !"));
    }

    // 4. View account details
    @GetMapping("/details/{accountId}")
    public ResponseEntity<?> getAccountDetails(@AuthenticationPrincipal Customer customer, @PathVariable Integer accountId) {
        return ResponseEntity.ok(accountService.getAccountDetails(customer.getId(), accountId));
    }

    //    List user's account
    @GetMapping("/list")
    public ResponseEntity<?> listAccounts(@AuthenticationPrincipal Customer customer) {
        return ResponseEntity.ok(accountService.getAllAccounts(customer.getId()));
    }

    //    6. Deposit (ايداع)
    @PutMapping("/deposit/{accountId}")
    public ResponseEntity<?> deposit(@AuthenticationPrincipal Customer customer, @PathVariable Integer accountId, @RequestParam Double amount) {
        accountService.deposit(customer.getId(), accountId, amount);
        return ResponseEntity.ok(new ApiResponse("Deposit successful!"));
    }

    //    6. withdraw (سحب)
    @PutMapping("/withdraw/{accountId}")
    public ResponseEntity<?> withdraw(@AuthenticationPrincipal Customer customer, @PathVariable Integer accountId, @RequestParam Double amount) {
        accountService.withdraw(customer.getId(), accountId, amount);
        return ResponseEntity.ok(new ApiResponse("Withdraw successful!"));
    }


    //    7. Transfer between accounts
    @PutMapping("/transfer/{fromAccountId}/{toAccountId}")
    public ResponseEntity<?> transfer(@AuthenticationPrincipal  Customer customer, @PathVariable Integer fromAccountId, @PathVariable Integer toAccountId, @RequestParam Double amount) {
        accountService.transfer(customer.getId(), fromAccountId, toAccountId, amount);
        return ResponseEntity.ok(new ApiResponse("Transfer successful!"));
    }

    //    8. Block bank account
    @PutMapping("/block/{accountId}")
    public ResponseEntity<?> blockAccount(@AuthenticationPrincipal  Customer customer, @PathVariable Integer accountId) {
        accountService.blockAccount(customer.getId(), accountId);
        return ResponseEntity.ok(new ApiResponse("Account blocked !"));
    }


}
