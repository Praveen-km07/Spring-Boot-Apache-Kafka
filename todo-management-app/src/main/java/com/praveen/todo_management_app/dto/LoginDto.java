package com.praveen.todo_management_app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "Login DTO transfers the data between client and server")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

    @Schema(description = "usernameOrEmail")
    private String usernameOrEmail;
    @Schema(description = "password")
    private String password;

}
