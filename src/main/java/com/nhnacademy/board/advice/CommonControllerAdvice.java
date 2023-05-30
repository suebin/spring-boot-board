package com.nhnacademy.board.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@ControllerAdvice
public class CommonControllerAdvice {

    @InitBinder
    void intBinder(WebDataBinder webDataBinder){
        webDataBinder.initDirectFieldAccess();
    }

    //http status: 404
    @ExceptionHandler(NoHandlerFoundException.class)
    public String notfound(){
        log.info("page not found!");
        return "error/404";
    }
    //http status: 500
    @ExceptionHandler(Throwable.class)
    public String internalServlerError(Model model, Throwable throwable ){
        log.error("internal server error:{}",throwable.getMessage(),throwable);
        model.addAttribute("message", throwable.getMessage());
        return "error/500";
    }

}
