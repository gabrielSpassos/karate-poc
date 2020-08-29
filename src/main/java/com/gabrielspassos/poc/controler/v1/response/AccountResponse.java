package com.gabrielspassos.poc.controler.v1.response;

import lombok.Data;

@Data
public class AccountResponse {

    private Long id;
    private Long bank;
    private Long agency;
    private Long number;

}
