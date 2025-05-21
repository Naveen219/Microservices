package com.nerdyprogrammer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(name = "ErrorResponseDto", description = "Schema to hold error response details")
public class ErrorResponseDto {

    @Schema(description = "API path invoked by the client")
    private String apiPath;

    @Schema(description = "Error code representing the error occurred")
    private HttpStatus errorCode;

    @Schema(description = "Error message representing the error occurred")
    private String errorMessage;

    @Schema(description = "Error time representing when the error occurred")
    private LocalDateTime errorTime;
}
