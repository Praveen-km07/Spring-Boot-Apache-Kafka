package com.praveen.banking.mapper;

import com.praveen.banking.dto.AccountDto;
import com.praveen.banking.entity.Account;

public class AccountMapper {

    public static Account mapToAccount(AccountDto accountDto){
        Account account = new Account(
//                accountDto.getId(),
//                accountDto.getAccountHolderName(),
//                accountDto.getBalance()
                accountDto.id(),
                accountDto.accountHolderName(),
                accountDto.balance()
        );
        return account;
    }

    public static AccountDto mapToAccountDto(Account account){
        AccountDto accountDto = new AccountDto(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance()
        );
        return accountDto;
    }
}
