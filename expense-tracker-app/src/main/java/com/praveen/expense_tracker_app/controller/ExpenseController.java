package com.praveen.expense_tracker_app.controller;

import com.praveen.expense_tracker_app.dto.ExpenseDto;
import com.praveen.expense_tracker_app.service.ExpenseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@AllArgsConstructor
@Tag(name="Expense-Tracker-App",description = "Operations related to Expenses")
public class ExpenseController {

    //inject the expenseService using constructor based DI
    private ExpenseService expenseService;

    //Build create expnse api
    @PostMapping
    @Operation(summary = "Creating the Expense",responses = {
            @ApiResponse(responseCode = "200",description = "creation of expenses successful"),
            @ApiResponse(responseCode = "201",description = "creation of expenses successful"),
            @ApiResponse(responseCode = "400",description = "creation of expense unsuccessful")
    })
    public ResponseEntity<ExpenseDto> createExpense(@RequestBody ExpenseDto expenseDto){
        return new ResponseEntity<>(expenseService.createExpense(expenseDto), HttpStatus.CREATED);
    }

    //Build get expense by id REST API
    @GetMapping("/{id}")
    @Operation(summary = "Getting specific expense by ID")
    public ResponseEntity<ExpenseDto> getExpensesById(@PathVariable Long id){
        ExpenseDto expenseDto = expenseService.getExpenseById(id);
        return ResponseEntity.ok(expenseDto);
    }

    @GetMapping
    @Operation(summary = "Get All expenses")
    public ResponseEntity<List<ExpenseDto>> getAllExpenses(){
        List<ExpenseDto> expense =expenseService.getAllExpenses();
        return ResponseEntity.ok(expense);
    }

    //Build update expense Rest API
    @PutMapping("/{id}")
    @Operation(summary = "Updating the existing expenses",responses = {
            @ApiResponse(responseCode = "200",description = "Updation is successful"),
            @ApiResponse(responseCode = "400",description = "Updation is unsuccessful")
    })
    public ResponseEntity<ExpenseDto> updateExpense(@PathVariable Long id,@RequestBody ExpenseDto expenseDto){
        ExpenseDto updateExpense= expenseService.updateExpense(id, expenseDto);
        return ResponseEntity.ok(updateExpense);
    }

    //Build delete expense Rest API
    @DeleteMapping("/{id}")
    @Operation(summary ="Deleting the expense",responses = {
            @ApiResponse(responseCode = "200",description = "deletion of expenses is successful"),
            @ApiResponse(responseCode = "400",description = "deletion of expenses is unsuccessful")
    })
    public ResponseEntity<String> deleteExpense(@PathVariable Long id){
         expenseService.deleteExpense(id);
        return ResponseEntity.ok("Deletion is successful");
    }
}
