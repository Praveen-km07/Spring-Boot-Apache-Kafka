package com.praveen.expense_tracker_app.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "Category DTO(Data Tansfer Object) to transfer the data between client and server"
)
public record CategoryDto(Long id,
                          @Schema(description = "Category Name") String categoryName) {
}
