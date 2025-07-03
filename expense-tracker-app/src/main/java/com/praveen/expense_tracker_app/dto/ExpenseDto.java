package com.praveen.expense_tracker_app.dto;

import com.praveen.expense_tracker_app.entity.Category;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ExpenseDto(Long id, BigDecimal amount, LocalDateTime expenseTimestamp, CategoryDto categoryDto) {
}
