package com.godev.budgetgo.infra.error.handler.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@ApiModel("Error info model")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
public class ErrorInfoDto {

    private int statusCode;

    private LocalDateTime timestamp = LocalDateTime.now();

    private String message;

    private String details;

    public ErrorInfoDto(HttpStatus httpStatus, String message) {
        this.statusCode = httpStatus.value();
        this.message = message;
    }

    public ErrorInfoDto(HttpStatus httpStatus, String message, String details) {
        this.statusCode = httpStatus.value();
        this.message = message;
        this.details = details;
    }
}
