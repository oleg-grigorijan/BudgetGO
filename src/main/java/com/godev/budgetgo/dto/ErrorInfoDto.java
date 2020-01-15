package com.godev.budgetgo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.godev.budgetgo.json.LocalDateTimeSerializer;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorInfoDto {

    private int statusCode;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime timestamp = LocalDateTime.now();

    private String message;

    private String details;

    public ErrorInfoDto() {
    }

    public ErrorInfoDto(HttpStatus httpStatus, String message) {
        setHttpStatus(httpStatus);
        this.message = message;
    }

    public ErrorInfoDto(HttpStatus httpStatus, String message, String details) {
        setHttpStatus(httpStatus);
        this.message = message;
        this.details = details;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.statusCode = httpStatus.value();
    }

    public int getStatusCode() {
        return statusCode;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
