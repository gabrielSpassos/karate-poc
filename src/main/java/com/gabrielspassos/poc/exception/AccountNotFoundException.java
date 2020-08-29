package com.gabrielspassos.poc.exception;

import com.gabrielspassos.poc.model.dto.ErrorDTO;
import org.springframework.http.HttpStatus;

public class AccountNotFoundException extends BusinessException {

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }

    @Override
    public ErrorDTO getError() {
        return new ErrorDTO("1", "Account not found");
    }
}
