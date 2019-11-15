package com.godev.budgetgo.advice;

import com.godev.budgetgo.dto.ErrorInfoDto;
import com.godev.budgetgo.exception.NotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundAdvice {

    @ExceptionHandler(NotFoundException.class)
    ErrorInfoDto handle(NotFoundException ex) {
        return new ErrorInfoDto(
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );
    }
}
