package com.praveen.banking.dto;

public record TransferFundDto(Long fromAccountId,Long toAccountId,double amount) {
}
