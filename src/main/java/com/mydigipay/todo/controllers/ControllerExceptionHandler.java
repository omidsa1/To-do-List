package com.mydigipay.todo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ControllerAdvice
@EnableWebMvc
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public @ResponseBody ErrorMessage handleException(RuntimeException ex){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setTimestamp(System.currentTimeMillis());
        errorMessage.setStatus(HttpStatus.BAD_REQUEST.value());
        errorMessage.setError(ex.getMessage());
        return errorMessage;
    }

    class ErrorMessage{
        private  Long timestamp;
        private  Integer status;
        private  String error;


        public ErrorMessage() {
        }

        public void setTimestamp(Long timestamp) {
            this.timestamp = timestamp;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public void setError(String error) {
            this.error = error;
        }

        public Long getTimestamp() {
            return timestamp;
        }

        public Integer getStatus() {
            return status;
        }

        public String getError() {
            return error;
        }
    }

}
