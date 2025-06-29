package com.praveen.banking.dto;

import java.time.LocalDateTime;

public record TransactionHistoryDto(Long id, Long accountId, double amount, String transactionType, LocalDateTime timeStamp) {
}
