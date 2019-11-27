package com.godev.budgetgo.advice;

import com.godev.budgetgo.dto.ErrorInfoDto;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenExceptionsHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public ErrorInfoDto handle(AccessDeniedException ex) {
        return new ErrorInfoDto(HttpStatus.FORBIDDEN, ex.getMessage());
    }
}