package com.idhit.hms.idhithealthclinic.exception;

import com.idhit.hms.idhithealthclinic.entity.Appointment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resource, Long id) {
        super("The " + resource + " resource with ID " + id + " you are looking for was not found.");
    }

    public ResourceNotFoundException(String resource){
        super("The " + resource + " resource you are looking for was not found.");
    }
}
