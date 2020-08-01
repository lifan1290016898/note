package com.demo.web.exception;

import com.demo.result.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ValidController {

    @ExceptionHandler(BindException.class)
    public Result handleErrArgs(BindException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        StringBuilder message = new StringBuilder();
        for(int i = 0; i < fieldErrors.size(); i++){
            FieldError fieldError = fieldErrors.get(i);
            message.append(fieldError.getDefaultMessage());
            if(i < fieldErrors.size() - 1){
                message.append("\r\n");
            }
        }
        return Result.fail(message.toString());
    }


}
