package com.spring.cloud.pagamento.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{


    private static final long serialVersionUID = 975916755677338405L;

    public ResourceNotFoundException(String exception){
        super(exception);
    }
}
