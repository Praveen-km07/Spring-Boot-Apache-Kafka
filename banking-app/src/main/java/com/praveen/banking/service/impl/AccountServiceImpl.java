package com.praveen.banking.service.impl;

import com.praveen.banking.dto.AccountDto;
import com.praveen.banking.dto.TransactionHistoryDto;
import com.praveen.banking.dto.TransferFundDto;
import com.praveen.banking.entity.Account;
import com.praveen.banking.entity.Transaction;
import com.praveen.banking.exception.AccountException;
import com.praveen.banking.mapper.AccountMapper;
import com.praveen.banking.repository.AccountRepository;
import com.praveen.banking.repository.TransactionRespository;
import com.praveen.banking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;
    private TransactionRespository transactionRespository;
    private static final String TRANSACTION_TYPE_DEPOSIT ="DEPOSIT";
    private static  final String TRANSACTION_TYPE_WITHDRAWAL="WITHDRAWAL";
    private static final String TRANSACTION_TYPE_TRANSFER="TRANSFER";

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository,TransactionRespository transactionRespository) {
        this.accountRepository = accountRepository;
        this.transactionRespository=transactionRespository;
    }


    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount=accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(()-> new AccountException("Account does not exists"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account =accountRepository.findById(id).orElseThrow(()->new AccountException("Account does not exists"));
        double total = account.getBalance() + amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        Transaction transaction = new Transaction();
        transaction.setAccountId(id);
        transaction.setAmount(amount);
        transaction.setTransactionType(TRANSACTION_TYPE_DEPOSIT);
        transaction.setTimestamp(LocalDateTime.now());
        transactionRespository.save(transaction);
        return AccountMapper.mapToAccountDto(savedAccount);

    }

    @Override
    public AccountDto withdrawal(Long id, double amount) {
        Account account = accountRepository.findById(id).orElseThrow(()->new AccountException("Account does not exists"));
        if(account.getBalance()<amount){
            throw new RuntimeException("Insufficient amount");
        }
        double total = account.getBalance() - amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        Transaction transaction =  new Transaction();
        transaction.setAccountId(id);
        transaction.setAmount(amount);
        transaction.setTransactionType(TRANSACTION_TYPE_WITHDRAWAL);
        transaction.setTimestamp(LocalDateTime.now());
        transactionRespository.save(transaction);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts=accountRepository.findAll();
        return accounts.stream().map((account)->AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());

    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(()->new AccountException("Account does not exists"));
        accountRepository.deleteById(id);
    }

    /**
     * @param transferFundDto
     */
    @Override
    public void transferFunds(TransferFundDto transferFundDto) {
        //Retrieve the account from which we send the amount
        Account fromAccount =accountRepository.findById(transferFundDto.fromAccountId()).orElseThrow(()->new AccountException("Account does not exists"));

        //Retrieve the account to which we send the amount
        Account toAccount = accountRepository.findById(transferFundDto.toAccountId()).orElseThrow(()->new AccountException("Account Does not exists"));
            if(fromAccount.getBalance() < transferFundDto.amount()){
                throw new RuntimeException("Insufficient Amount");
            }
        //Debit the amount from fromAccount object
        fromAccount.setBalance(fromAccount.getBalance() - transferFundDto.amount());

        //Credit the amount from toAccount object
        toAccount.setBalance(toAccount.getBalance()+ transferFundDto.amount());

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        Transaction transaction= new Transaction();
        transaction.setAccountId(transferFundDto.fromAccountId());
        transaction.setAmount(transferFundDto.amount());
        transaction.setTransactionType(TRANSACTION_TYPE_TRANSFER);
        transaction.setTimestamp(LocalDateTime.now());
        transactionRespository.save(transaction);
    }

    /**
     * @param accountId
     * @return
     */
    @Override
    public List<TransactionHistoryDto> getAccountTransactions(Long accountId) {
        List<Transaction> transactions =transactionRespository.findByAccountIdOrderByTimestampDesc(accountId);
        return transactions.stream().map((transaction)->convertEntityToDto(transaction)).collect(Collectors.toList());

    }
    private TransactionHistoryDto convertEntityToDto(Transaction transaction){
    return new TransactionHistoryDto(
        transaction.getId(),
        transaction.getAccountId(),
        transaction.getAmount(),
        transaction.getTransactionType(),
        transaction.getTimestamp());
    }

}
