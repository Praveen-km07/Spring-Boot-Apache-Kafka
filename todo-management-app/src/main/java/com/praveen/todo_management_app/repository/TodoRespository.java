package com.praveen.todo_management_app.repository;

import com.praveen.todo_management_app.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRespository extends JpaRepository<Todo,Long> {
}
