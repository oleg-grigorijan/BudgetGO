package com.godev.budgetgo.infra.error.handler;

import com.godev.budgetgo.infra.error.exception.NotFoundException;
import com.godev.budgetgo.infra.error.handler.dto.ErrorInfoDto;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundExceptionsHandler {

    @ExceptionHandler(NotFoundException.class)
    public ErrorInfoDto handle(NotFoundException ex) {
        return new ErrorInfoDto(HttpStatus.NOT_FOUND, ex.getMessage());
    }
}
