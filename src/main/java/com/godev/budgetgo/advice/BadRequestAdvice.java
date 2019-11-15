package com.godev.budgetgo.advice;

import com.godev.budgetgo.dto.ErrorInfoDto;
import com.godev.budgetgo.exception.BadRequestException;
import com.godev.budgetgo.exception.UnprocessableEntityException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestAdvice {

    @ExceptionHandler(BadRequestException.class)
    ErrorInfoDto handle(BadRequestException ex) {
        return new ErrorInfoDto(
                HttpStatus.BAD_REQUEST,
                ex.getMessage()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ErrorInfoDto handle(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(
                error -> errors.add(error.getField() + ": " + error.getDefaultMessage())
        );
        return new ErrorInfoDto(
                HttpStatus.BAD_REQUEST,
                "Validation error",
                String.join("; ", errors)
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    ErrorInfoDto handle(HttpServletRequest request, HttpMessageNotReadableException ex) {
        return new ErrorInfoDto(
                HttpStatus.BAD_REQUEST,
                "Request parsing error"
        );
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    ErrorInfoDto handle(UnprocessableEntityException ex) {
        ErrorInfoDto dto = new ErrorInfoDto(HttpStatus.BAD_REQUEST, ex.getMessage());
        if (ex.getCause() != null) {
            dto.setDetails(ex.getCause().getMessage());
        }
        return dto;
    }
}