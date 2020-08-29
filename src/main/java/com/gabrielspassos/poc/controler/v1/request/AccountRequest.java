package com.gabrielspassos.poc.controler.v1.request;

import lombok.Data;

@Data
public class AccountRequest {

    private Long bank;
    private Long agency;
    private Long number;

}
