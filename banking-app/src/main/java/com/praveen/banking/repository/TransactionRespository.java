package com.praveen.banking.repository;

import com.praveen.banking.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRespository extends JpaRepository<Transaction,Long> {
    List<Transaction> findByAccountIdOrderByTimestampDesc(Long accountId);

}
