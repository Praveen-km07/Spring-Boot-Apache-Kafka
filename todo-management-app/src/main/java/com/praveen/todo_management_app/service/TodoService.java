package com.praveen.todo_management_app.service;

import com.praveen.todo_management_app.dto.TodoDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TodoService {
    TodoDto addTodo(TodoDto todoDto);

    TodoDto getTodoById(Long id);

    List<TodoDto> getAllTodos();

    TodoDto updateTodoById(TodoDto todoDto,Long id);

    void deleteTodo(Long id);

    TodoDto completeTodo(Long id);

    TodoDto inCompleteTodo(Long id);
}
