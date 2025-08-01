package com.praveen.todo_management_app.repository;

import com.praveen.todo_management_app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String username);

    Boolean existsByEmail(String email);
    Optional<User> findByUsernameOrEmail(String username,String email);

    Boolean existsByUsername(String username);
}
