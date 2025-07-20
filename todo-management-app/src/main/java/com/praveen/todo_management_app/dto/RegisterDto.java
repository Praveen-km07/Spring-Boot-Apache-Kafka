package com.praveen.todo_management_app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "Register DTO transfers the data between client and server")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {

    @Schema(description = "name")
    private String name;
    @Schema(description = "username")
    private String username;
    @Schema(description = "email")
    private String email;
    @Schema(description = "password")
    private String password;


}
