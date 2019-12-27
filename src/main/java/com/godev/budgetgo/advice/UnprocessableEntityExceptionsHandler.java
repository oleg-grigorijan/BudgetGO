package com.godev.budgetgo.advice;

import com.godev.budgetgo.dto.ErrorInfoDto;
import com.godev.budgetgo.exception.BalanceOverflowException;
import com.godev.budgetgo.exception.UnprocessableEntityException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessableEntityExceptionsHandler {

    @ExceptionHandler(BalanceOverflowException.class)
    public ErrorInfoDto handle(BalanceOverflowException ex) {
        return new ErrorInfoDto(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public ErrorInfoDto handle(UnprocessableEntityException ex) {
        ErrorInfoDto dto = new ErrorInfoDto(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
        if (ex.getCause() != null) {
            dto.setDetails(ex.getCause().getMessage());
        }
        return dto;
    }
}
