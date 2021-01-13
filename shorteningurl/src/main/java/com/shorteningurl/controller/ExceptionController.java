package com.shorteningurl.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value=IllegalArgumentException.class)
    public String handlerBaseException(IllegalArgumentException e){
        return "wrong_url";
    }
}
