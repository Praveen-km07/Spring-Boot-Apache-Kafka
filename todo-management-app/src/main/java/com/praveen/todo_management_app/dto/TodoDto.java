package com.praveen.todo_management_app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description="Todo DTO transfers the data between client and server")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TodoDto {

    private Long id;
    @Schema(description = "title")
    private String title;
    @Schema(description = "description")
    private String description;

    @Schema(description="completed")
    private boolean completed;

}
