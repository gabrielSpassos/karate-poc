package com.gabrielspassos.poc.model.builder;

import com.gabrielspassos.poc.controler.v1.request.AccountParamRequest;
import com.gabrielspassos.poc.model.dto.AccountDTO;
import com.gabrielspassos.poc.model.dto.AccountParamDTO;

public class AccountParamBuilder {

    public static AccountParamDTO build(AccountParamRequest accountParamRequest) {
        return AccountParamDTO.builder()
                .bank(accountParamRequest.getBank())
                .agency(accountParamRequest.getAgency())
                .number(accountParamRequest.getNumber())
                .build();
    }

    public static AccountParamDTO build(AccountDTO accountDTO) {
        return AccountParamDTO.builder()
                .bank(accountDTO.getBank())
                .agency(accountDTO.getAgency())
                .number(accountDTO.getNumber())
                .build();
    }
}
