package com.gabrielspassos.poc.exception;

import com.gabrielspassos.poc.model.dto.ErrorDTO;
import org.springframework.http.HttpStatus;

public abstract class BusinessException extends RuntimeException{

    public abstract HttpStatus getHttpStatus();

    public abstract ErrorDTO getError();

}
