package com.gabrielspassos.poc.controler.v1.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AccountRequest {

    @NotEmpty(message = "Must inform account bank")
    private Long bank;
    @NotEmpty(message = "Must inform account agency")
    private Long agency;
    @NotEmpty(message = "Must inform account number")
    private Long number;

}
