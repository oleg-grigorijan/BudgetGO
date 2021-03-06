package com.godev.budgetgo.infra.error.handler;

import com.godev.budgetgo.infra.error.handler.dto.ErrorInfoDto;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class MethodNotAllowedExceptionsHandler {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ErrorInfoDto handle(
            HttpRequestMethodNotSupportedException ex,
            HttpServletResponse response
    ) {
        if (ex.getSupportedMethods() != null) {
            response.addHeader("Allow", String.join(", ", ex.getSupportedMethods()));
        }

        return new ErrorInfoDto(HttpStatus.METHOD_NOT_ALLOWED, ex.getMessage());
    }
}
