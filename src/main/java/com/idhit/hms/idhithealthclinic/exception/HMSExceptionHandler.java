package com.idhit.hms.idhithealthclinic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice
public class HMSExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> exception(ResourceNotFoundException rnfExc, WebRequest webRequest){
        ErrorDetails details = new ErrorDetails();
        details.setDate(new Date());
        details.setMessage(rnfExc.getMessage());
        return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
    }

}
