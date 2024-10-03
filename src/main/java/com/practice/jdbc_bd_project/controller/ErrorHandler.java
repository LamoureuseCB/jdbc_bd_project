package com.practice.jdbc_bd_project.controller;

import com.practice.jdbc_bd_project.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler()
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse objectFoundHandle(final NotFoundException e) {
        return new ErrorResponse("Объект не найден", e.getMessage());

    }
}