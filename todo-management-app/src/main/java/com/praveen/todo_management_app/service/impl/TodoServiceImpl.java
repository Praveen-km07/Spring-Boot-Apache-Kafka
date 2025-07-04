package com.praveen.todo_management_app.service.impl;

import com.praveen.todo_management_app.dto.TodoDto;
import com.praveen.todo_management_app.entity.Todo;

import com.praveen.todo_management_app.exception.ResourceNotFoundException;
import com.praveen.todo_management_app.repository.TodoRespository;
import com.praveen.todo_management_app.service.TodoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

    private TodoRespository todoRespository;

    private ModelMapper modelMapper;
    /**
     * @param todoDto
     * @return
     */
    @Override
    public TodoDto addTodo(TodoDto todoDto) {

        //Todo todo = TodoMapper.mapToTodo(todoDto);
        //Using modelmapper liberary
        Todo todo =modelMapper.map(todoDto,Todo.class);
        Todo savedTodo = todoRespository.save(todo);

        //TodoMapper.mapToTodoDto(savedTodo);
        return modelMapper.map(savedTodo,TodoDto.class);
    }

    /**
     * @param id
     * @return
     */

    @Override
    public TodoDto getTodoById(Long id) {
        Todo todo=todoRespository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Todo not present with id :" +id));
        return modelMapper.map(todo,TodoDto.class);
    }

    /**
     * @return
     */
    @Override
    public List<TodoDto> getAllTodos() {
        List<Todo> todoList = todoRespository.findAll();
        return todoList.stream().map(todo -> modelMapper.map(todo,TodoDto.class)).collect(Collectors.toList());
    }

    /**
     * @param id
     * @param title
     * @param description
     * @param completed
     * @return
     */
    @Override
    public TodoDto updateTodoById(TodoDto todoDto,Long id) {
        Todo todo =todoRespository.findById(id).orElseThrow(()->new ResourceNotFoundException("Todo not present with id : "+id));
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());
        Todo updatedTodos=todoRespository.save(todo);
        return modelMapper.map(updatedTodos,TodoDto.class);
    }

    /**
     * @param id
     */
    @Override
    public void deleteTodo(Long id) {
        Todo todo = todoRespository.findById(id).orElseThrow(()->new ResourceNotFoundException("Todo not present with id :" +id));
        todoRespository.delete(todo);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public TodoDto completeTodo(Long id) {
        Todo todo=todoRespository.findById(id).orElseThrow(()->new ResourceNotFoundException("Todo not present with id :"+id));
        todo.setCompleted(Boolean.TRUE);
        Todo updatedcompleteTodo = todoRespository.save(todo);
        return modelMapper.map(updatedcompleteTodo,TodoDto.class);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public TodoDto inCompleteTodo(Long id) {
        Todo todo = todoRespository.findById(id).orElseThrow(()->new ResourceNotFoundException("Todo not present with id :"+id));
        todo.setCompleted(Boolean.FALSE);
        Todo updatedinComplete = todoRespository.save(todo);
        return modelMapper.map(updatedinComplete,TodoDto.class);
    }
}
