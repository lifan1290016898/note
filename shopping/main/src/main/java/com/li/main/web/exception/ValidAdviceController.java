package com.li.main.web.exception;

import com.li.main.result.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ValidAdviceController {

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
