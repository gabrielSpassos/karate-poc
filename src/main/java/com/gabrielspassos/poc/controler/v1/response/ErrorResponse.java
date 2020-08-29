package com.gabrielspassos.poc.controler.v1.response;

import lombok.Data;

@Data
public class ErrorResponse {

    private String code;
    private String message;
}
