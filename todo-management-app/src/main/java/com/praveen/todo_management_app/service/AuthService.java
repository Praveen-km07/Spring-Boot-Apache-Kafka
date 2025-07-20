package com.praveen.todo_management_app.service;

import com.praveen.todo_management_app.dto.LoginDto;
import com.praveen.todo_management_app.dto.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);

    String login(LoginDto loginDto);
}
