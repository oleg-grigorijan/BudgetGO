package com.godev.budgetgo.advice;

import com.godev.budgetgo.dto.ErrorInfoDto;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerErrorExceptionsHandler {

    private final static Logger logger = LogManager.getLogger(InternalServerErrorExceptionsHandler.class);

    @ExceptionHandler(Exception.class)
    public ErrorInfoDto handle(Exception ex) {
        logger.error("Unprocessed exception", ex);
        return new ErrorInfoDto(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
    }
}
