package com.praveen.expense_tracker_app.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Schema(description = "Error DTO(Data Tansfer Object) to transfer the data between client and server")
@Getter
@Setter
public class ErrorDetails {
    @Schema(description = "Error occured TimeStamp")
    private LocalDateTime timestamp;
    @Schema(description ="Error message " )
    private String message;
    @Schema(description = "Error Details")
    private String details;

    @Schema(description = "Error Code")
    private String errorCode;
}
