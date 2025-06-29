package com.praveen.banking.controller;

import com.praveen.banking.dto.AccountDto;
import com.praveen.banking.dto.TransactionHistoryDto;
import com.praveen.banking.dto.TransferFundDto;
import com.praveen.banking.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
@Tag(name="Banking-App",description = "Operations Related to Banking")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    //Add Account Rest API
    @PostMapping
    @Operation(summary = "Creating the Account",responses={@ApiResponse(responseCode = "200",description="Creating the account is successful"),
            @ApiResponse(responseCode = "201",description = "Creating account is successful"),
            @ApiResponse(responseCode = "400",description = "Creating Account is unsuccessfull")})
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Account by ID")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable  Long id){
        AccountDto accountDto = accountService.getAccountById(id);
        return  ResponseEntity.ok(accountDto);
    }

    //Deposit REST API
    @PutMapping("/{id}/deposit")
    @Operation(summary = "Depositing the amount",responses = {
            @ApiResponse(responseCode = "200",description = "Amount deposited successful"),
            @ApiResponse(responseCode = "400",description = "Depositing the amount is unsuccessful")
    })
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id,@RequestBody Map<String,Double> request){
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.deposit(id,amount);
        return ResponseEntity.ok(accountDto);
    }

    //Withdrawal
    @PutMapping("/{id}/withdrawal")
    @Operation(summary = "Amount withdrawal",responses = {
            @ApiResponse(responseCode = "200",description="Amount withdrawal is successful"),
            @ApiResponse(responseCode = "400",description = "Amount withdrawal is unsuccessful")
    })
    public ResponseEntity<AccountDto> withdrawal(@PathVariable Long id,@RequestBody Map<String,Double> request){
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.withdrawal(id,amount);
        return ResponseEntity.ok(accountDto);
    }

    //Get All Accounts
    @GetMapping
    @Operation(summary = "Get All Accounts")
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
        List<AccountDto> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);

    }

    //Delete account
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletion of account",responses = {
            @ApiResponse(responseCode = "200",description = "Deletion of account successful"),
            @ApiResponse(responseCode = "400",description="Deletion of account unsuccessful")
    })
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account is Deleted successfully");
    }

    //Build transfer REST aPI
    @PostMapping("/transfer")
    @Operation(summary = "Transfering the Fund",responses = {
            @ApiResponse(responseCode = "200",description = "Transfering the fund is successful"),
            @ApiResponse(responseCode = "400",description = "Transfering the fund is unsuccessful")
    })
    public ResponseEntity<String> transferFund(@RequestBody TransferFundDto transferFundDto){
        accountService.transferFunds(transferFundDto);
        return ResponseEntity.ok("Transfer Successful");
    }

    //Build Transaction History API
    @GetMapping("/{id}/transactions")
    @Operation(summary = "Get Transaction History of the Account")
    public ResponseEntity<List<TransactionHistoryDto>> fetchAccountTransactions(@PathVariable("id") Long accountId){
        List<TransactionHistoryDto> transactions=accountService.getAccountTransactions(accountId);
        return ResponseEntity.ok(transactions);
    }
}
