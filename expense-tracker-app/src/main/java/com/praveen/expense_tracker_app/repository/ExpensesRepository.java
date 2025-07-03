package com.praveen.expense_tracker_app.repository;

import com.praveen.expense_tracker_app.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpensesRepository extends JpaRepository<Expense,Long> {
}
