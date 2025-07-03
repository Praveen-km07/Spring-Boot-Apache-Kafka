package com.praveen.expense_tracker_app.mapper;

import com.praveen.expense_tracker_app.dto.CategoryDto;
import com.praveen.expense_tracker_app.dto.ExpenseDto;
import com.praveen.expense_tracker_app.entity.Category;
import com.praveen.expense_tracker_app.entity.Expense;

public class ExpensesMapper {

    public static  ExpenseDto mapToExpenseDto(Expense expense){
        ExpenseDto expenseDto = new ExpenseDto(
                expense.getId(),
                expense.getAmount(),
                expense.getExpenseTimestamp(),
                new CategoryDto(
                        expense.getCategory().getId(),
                        expense.getCategory().getCategoryName()
                )
        );
        return expenseDto;
    }

    public static Expense mapToExpense(ExpenseDto expenseDto){
        Category category = new Category();
        category.setId(expenseDto.categoryDto().id());
         Expense expense = new Expense(
                          expenseDto.id(),
                 expenseDto.amount(),
                 expenseDto.expenseTimestamp(),
                  category
                  );
         return expense;
    }


}
