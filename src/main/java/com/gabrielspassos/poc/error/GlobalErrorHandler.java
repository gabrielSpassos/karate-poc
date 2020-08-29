package com.gabrielspassos.poc.error;

import com.gabrielspassos.poc.exception.BusinessException;
import com.gabrielspassos.poc.model.dto.ErrorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class GlobalErrorHandler {


    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public ResponseEntity<ErrorDTO> handleBusinessException(BusinessException businessException) {
        log.error("Expected Error", businessException);
        return ResponseEntity
                .status(businessException.getHttpStatus())
                .body(businessException.getError());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult().getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .findFirst()
                .orElse(e.getMessage());

        return buildError("9", errorMessage, e);
    }

    private ErrorDTO buildError(String code, String message, Exception e) {
        log.error("Error code {}, Error Message {}", code, message, e);
        return new ErrorDTO(code, message);
    }

}
