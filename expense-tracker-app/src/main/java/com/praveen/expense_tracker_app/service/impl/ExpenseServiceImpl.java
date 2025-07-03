package com.praveen.expense_tracker_app.service.impl;

import com.praveen.expense_tracker_app.dto.ExpenseDto;
import com.praveen.expense_tracker_app.entity.Category;
import com.praveen.expense_tracker_app.entity.Expense;
import com.praveen.expense_tracker_app.exception.ResourceNotFoundException;
import com.praveen.expense_tracker_app.mapper.ExpensesMapper;
import com.praveen.expense_tracker_app.repository.CategoryRepository;
import com.praveen.expense_tracker_app.repository.ExpensesRepository;
import com.praveen.expense_tracker_app.service.ExpenseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ExpenseServiceImpl implements ExpenseService {

    private ExpensesRepository expensesRepository;

    private CategoryRepository categoryRepository;

    /**
     * @param expenseDto
     * @return
     */
    @Override
    public ExpenseDto createExpense(ExpenseDto expenseDto) {
        //Convert expenseDto to expense entity
        Expense expense = ExpensesMapper.mapToExpense(expenseDto);
        //save expense entity to database
        Expense saveExpense =expensesRepository.save(expense);
        //convert saved expense entity into expensedto

        return ExpensesMapper.mapToExpenseDto(saveExpense);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public ExpenseDto getExpenseById(Long id) {
        Expense expense = expensesRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Expense Does not exists with id : " +id));
        return ExpensesMapper.mapToExpenseDto(expense);
    }

    /**
     * @return
     */
    @Override
    public List<ExpenseDto> getAllExpenses() {
        List<Expense> expenseList = expensesRepository.findAll();
        return expenseList.stream().map(expense -> ExpensesMapper.mapToExpenseDto(expense)).collect(Collectors.toList());
    }

    /**
     * @param expenseId
     * @param expenseDto
     * @return
     */
    @Override
    public ExpenseDto updateExpense(Long expenseId, ExpenseDto expenseDto) {
        Expense expense =expensesRepository.findById(expenseId).orElseThrow(()->new ResourceNotFoundException("Expense  not found with id : "+expenseId));
        expense.setAmount(expenseDto.amount());
        expense.setExpenseTimestamp(expenseDto.expenseTimestamp());
        if(expenseDto.categoryDto() !=null){
           Category category = categoryRepository.findById(expenseDto.categoryDto().id()).orElseThrow(()->new ResourceNotFoundException("Expense not found with id :" +expenseDto.categoryDto().id()));
              expense.setCategory(category);
        }
        //update expense entity
        Expense updateExpense =expensesRepository.save(expense);
        return ExpensesMapper.mapToExpenseDto(updateExpense);

    }

    /**
     * @param id
     */
    @Override
    public void deleteExpense(Long id) {
        Expense expense = expensesRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Expense does not exists with id :" +id));
        expensesRepository.delete(expense);
    }
}
