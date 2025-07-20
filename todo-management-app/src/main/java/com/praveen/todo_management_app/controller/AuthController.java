package com.praveen.todo_management_app.controller;

import com.praveen.todo_management_app.dto.LoginDto;
import com.praveen.todo_management_app.dto.RegisterDto;
import com.praveen.todo_management_app.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name= "CRUD Operations related to auth resource",description ="CRUD REST APIs for auth Resource -Create user" +
        "Update user,Get user and Delete user" )
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private AuthService authService;

    //Build Register REST API
    @Operation(summary = "Register the user",description = "Registering the user",responses = {
            @ApiResponse(responseCode = "200",description = "User has been successfully registered"),
            @ApiResponse(responseCode = "201",description = "User has been succeesfully registered"),
            @ApiResponse(responseCode = "500",description = "Unable to register the user")
    })
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //Build Login REST AI
    @Operation(summary = "Login the user",description = "Login the user",responses = {
            @ApiResponse(responseCode = "200",description = "User has been successfully logged in"),
            @ApiResponse(responseCode = "201",description = "User has been succeesfully logged in"),
            @ApiResponse(responseCode = "500",description = "Unable to login")
    })
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
        String response = authService.login(loginDto);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
