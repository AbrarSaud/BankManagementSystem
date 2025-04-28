package com.example.bankmanagementsystem.Controller;

import com.example.bankmanagementsystem.Api.ApiResponse;
import com.example.bankmanagementsystem.DTO.AccountDTO;
import com.example.bankmanagementsystem.Model.Account;
import com.example.bankmanagementsystem.Model.MyUser;
import com.example.bankmanagementsystem.Service.AccountService;
import com.example.bankmanagementsystem.Service.AuthService;
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
    public ResponseEntity<?> createAccount(@AuthenticationPrincipal MyUser user, @Valid @RequestBody AccountDTO accountDTO) {
        accountService.createAccount(user.getId(), accountDTO);
        return ResponseEntity.ok(new ApiResponse("Account created !"));
    }

    // 3. Active a bank account
    @PutMapping("/activate/{accountId}")
    public ResponseEntity<?> activateAccount(@AuthenticationPrincipal MyUser user, @PathVariable Integer accountId) {
        accountService.activateAccount(user.getId(), accountId);
        return ResponseEntity.ok(new ApiResponse("Account activated !"));
    }

    // 4. View account details
    @GetMapping("/details/{accountId}")
    public ResponseEntity<?> getAccountDetails(@AuthenticationPrincipal MyUser user, @PathVariable Integer accountId) {
        return ResponseEntity.ok(accountService.getAccountDetails(user.getId(), accountId));
    }

    //    List user's account
    @GetMapping("/list")
    public ResponseEntity<?> listAccounts(@AuthenticationPrincipal MyUser user) {
        return ResponseEntity.ok(accountService.getAllAccounts(user.getId()));
    }

    //    6. Deposit (ايداع)
    @PutMapping("/deposit/{accountId}")
    public ResponseEntity<?> deposit(@AuthenticationPrincipal MyUser user, @PathVariable Integer accountId, @RequestParam Double amount) {
        accountService.deposit(user.getId(), accountId, amount);
        return ResponseEntity.ok(new ApiResponse("Deposit successful!"));
    }

    //    6. withdraw (سحب)
    @PutMapping("/withdraw/{accountId}")
    public ResponseEntity<?> withdraw(@AuthenticationPrincipal MyUser user, @PathVariable Integer accountId, @RequestParam Double amount) {
        accountService.withdraw(user.getId(), accountId, amount);
        return ResponseEntity.ok(new ApiResponse("Withdraw successful!"));
    }


    //    7. Transfer between accounts
    @PutMapping("/transfer/{fromAccountId}/{toAccountId}")
    public ResponseEntity<?> transfer(@AuthenticationPrincipal MyUser user, @PathVariable Integer fromAccountId, @PathVariable Integer toAccountId, @RequestParam Double amount) {
        accountService.transfer(user.getId(), fromAccountId, toAccountId, amount);
        return ResponseEntity.ok(new ApiResponse("Transfer successful!"));
    }

    //    8. Block bank account
    @PutMapping("/block/{accountId}")
    public ResponseEntity<?> blockAccount(@AuthenticationPrincipal MyUser user, @PathVariable Integer accountId) {
        accountService.blockAccount(user.getId(), accountId);
        return ResponseEntity.ok(new ApiResponse("Account blocked !"));
    }


}
