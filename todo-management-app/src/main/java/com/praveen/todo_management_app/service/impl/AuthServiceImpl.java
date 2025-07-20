package com.praveen.todo_management_app.service.impl;

import com.praveen.todo_management_app.dto.LoginDto;
import com.praveen.todo_management_app.dto.RegisterDto;
import com.praveen.todo_management_app.entity.Role;
import com.praveen.todo_management_app.entity.User;
import com.praveen.todo_management_app.exception.TodoAPIException;
import com.praveen.todo_management_app.repository.RoleRepository;
import com.praveen.todo_management_app.repository.UserRepository;
import com.praveen.todo_management_app.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager;
    /**
     * @param registerDto
     * @return
     */
    @Override
    public String register(RegisterDto registerDto) {
        //check username is already exists in database
        if(userRepository.existsByUsername(registerDto.getUsername())){
            throw  new TodoAPIException(HttpStatus.BAD_REQUEST,"Username is already exists");
        }
        //check email is already exists in database
        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw new TodoAPIException(HttpStatus.BAD_REQUEST,"Email is already  exists");
        }
        User user =new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new TodoAPIException(HttpStatus.NOT_FOUND, "Role not found: ROLE_USER"));

        Set<Role> roles = new HashSet<>();

        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);
        return "User Registered successfully";
    }

    /**
     * @param loginDto
     * @return
     */
    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),
                loginDto.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "User logged in successfully";
    }
}
