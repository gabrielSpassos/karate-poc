package com.gabrielspassos.poc.error;

import com.gabrielspassos.poc.controler.v1.response.ErrorResponse;
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
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException businessException) {
        log.error("Expected Error", businessException);
        ErrorResponse errorResponse = buildError(businessException.getError());

        return ResponseEntity
                .status(businessException.getHttpStatus())
                .body(errorResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult().getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .findFirst()
                .orElse(e.getMessage());

        return buildError("9", errorMessage, e);
    }

    private ErrorResponse buildError(String code, String message, Exception e) {
        log.error("Error code {}, Error Message {}", code, message, e);
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(code);
        errorResponse.setMessage(message);
        return errorResponse;
    }

    private ErrorResponse buildError(ErrorDTO errorDTO) {
        log.error("Error {}", errorDTO);
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(errorDTO.getCode());
        errorResponse.setMessage(errorDTO.getMessage());
        return errorResponse;
    }

}
