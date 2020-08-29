package com.gabrielspassos.poc.exception;

import com.gabrielspassos.poc.model.dto.ErrorDTO;
import org.springframework.http.HttpStatus;

public class AccountAlreadyExistentException extends BusinessException {

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    public ErrorDTO getError() {
        return new ErrorDTO("2", "Account already existent");
    }
}
