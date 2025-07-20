package com.praveen.todo_management_app.controller;

import com.praveen.todo_management_app.dto.TodoDto;
import com.praveen.todo_management_app.entity.Todo;
import com.praveen.todo_management_app.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.prefs.AbstractPreferences;

@Tag(name= "CRUD Operations related to todo resource",description ="CRUD REST APIs for todo Resource -Create todo" +
        "Update todo,Get todo and Delete todo" )
@RestController
@RequestMapping("/api/todos")
@AllArgsConstructor
public class TodoController {

    private TodoService todoService;
    @Operation(summary = "Creating todos",description = "Creating todos",responses = {
            @ApiResponse(responseCode = "201",description = "Creation of todo is successful"),
            @ApiResponse(responseCode = "500",description = "Creation of todo is unsuccessful")
    })

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TodoDto> createTodos(@RequestBody TodoDto todoDto){
        System.out.println("Incoming DTO: " + todoDto);
        return new ResponseEntity<>(todoService.addTodo(todoDto), HttpStatus.CREATED);
    }

    @Operation(summary="Get todo by id",description = "Fetching specific todos by ID")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/{id}")
    public ResponseEntity<TodoDto> getTodoById(@PathVariable Long id){
         TodoDto todoDto = todoService.getTodoById(id);
         return ResponseEntity.ok(todoDto);
    }

    @Operation(summary = "Get All Todos",description = "Fetching all the todos")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos(){
        List<TodoDto> todoDtoList = todoService.getAllTodos();
        return new ResponseEntity<>(todoDtoList,HttpStatus.OK);
    }

    @Operation(summary = "Update todo by id",description="Update specific todo by id",responses = {
            @ApiResponse(responseCode = "200",description = "Updation of todo is successfull"),
            @ApiResponse(responseCode = "500",description = "Updation of todo is unsuccessfull")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<TodoDto> updateTodoByIdService(@PathVariable Long id,@RequestBody TodoDto todoDto){
         TodoDto updatedTodo=todoService.updateTodoById(todoDto,id);
        return  ResponseEntity.ok(updatedTodo);
    }

    @Operation(summary ="deleting the todos",description = "Deleting Todos by Id",responses = {
            @ApiResponse(responseCode = "200",description = "Deletion of todos is successful"),
            @ApiResponse(responseCode = "500",description = "Deletion of todos is unsuccessful")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
   public ResponseEntity<String> deleteTodo(@PathVariable Long id){
             todoService.deleteTodo(id);
        return ResponseEntity.ok("deletion of todo is successful");
   }

   @Operation(summary = "Update complete in todo",description = "Updating complete field",responses = {
           @ApiResponse(responseCode = "200",description = "updation is successful"),
           @ApiResponse(responseCode = "500",description = "updation is unsuccessful")
   })
   @PatchMapping("/{id}/complete")
   @PreAuthorize("hasAnyRole('ADMIN','USER')")
   public ResponseEntity<TodoDto> updatedCompleteTodos(@PathVariable Long id){
          TodoDto tododto=todoService.completeTodo(id);
          return ResponseEntity.ok(tododto);
   }

   @Operation(summary="updating as incomplete in todo",description = "Updating as incomplete ",responses = {
           @ApiResponse(responseCode = "200",description = "Updation is successfull"),
           @ApiResponse(responseCode = "500",description = "Updation is unsuccessfull")
   })
   @PreAuthorize("hasAnyRole('ADMIN','USER')")
   @PatchMapping("/{id}/incomplete")
   public ResponseEntity<TodoDto> incompleteTodos(@PathVariable Long id){
        TodoDto todoDto = todoService.inCompleteTodo(id);
        return ResponseEntity.ok(todoDto);
   }
}
