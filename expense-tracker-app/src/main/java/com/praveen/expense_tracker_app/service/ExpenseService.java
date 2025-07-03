package com.praveen.expense_tracker_app.service;

import com.praveen.expense_tracker_app.dto.ExpenseDto;

import java.util.List;

public interface ExpenseService {
    ExpenseDto createExpense(ExpenseDto expenseDto);

    ExpenseDto getExpenseById(Long id);

    List<ExpenseDto> getAllExpenses();

    ExpenseDto updateExpense(Long expenseId,ExpenseDto expenseDto);

    void deleteExpense(Long id);
}
