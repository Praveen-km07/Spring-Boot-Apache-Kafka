package com.praveen.expense_tracker_app.dto;

import com.praveen.expense_tracker_app.entity.Category;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Schema(description="Expense DTO(Data Tansfer Object) to transfer the data between client and server")

public record ExpenseDto(Long id, @Schema(description = "Amount") BigDecimal amount,@Schema(description = "ExpenseTimestamp") LocalDateTime expenseTimestamp,
                         @Schema(description = "Associated Expense Category") CategoryDto categoryDto) {
}
