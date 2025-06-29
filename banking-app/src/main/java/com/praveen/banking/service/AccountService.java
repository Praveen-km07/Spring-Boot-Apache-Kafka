package com.praveen.banking.service;

import com.praveen.banking.dto.AccountDto;
import com.praveen.banking.dto.TransactionHistoryDto;
import com.praveen.banking.dto.TransferFundDto;

import java.util.List;


public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(Long id);

    AccountDto deposit(Long id,double amount);

    AccountDto withdrawal(Long id,double amount);

    List<AccountDto> getAllAccounts();

    void deleteAccount(Long id);

    void transferFunds(TransferFundDto transferFundDto);

    List<TransactionHistoryDto> getAccountTransactions(Long accountId);
}
