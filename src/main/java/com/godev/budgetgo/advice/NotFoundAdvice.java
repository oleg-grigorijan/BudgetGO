package com.godev.budgetgo.advice;

import com.godev.budgetgo.exception.NotFoundExcepion;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NotFoundAdvice {

    @ExceptionHandler(NotFoundExcepion.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void handle() {
    }
}
