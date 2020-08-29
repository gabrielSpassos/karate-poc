package com.gabrielspassos.poc.model.builder;

import com.gabrielspassos.poc.controler.v1.request.AccountRequest;
import com.gabrielspassos.poc.model.dto.AccountDTO;
import com.gabrielspassos.poc.model.entity.AccountEntity;

public class AccountBuilder {

    public static AccountDTO build(AccountRequest accountRequest) {
        return AccountDTO.builder()
                .id(null)
                .bank(accountRequest.getBank())
                .agency(accountRequest.getAgency())
                .number(accountRequest.getNumber())
                .build();
    }

    public static AccountDTO build(AccountEntity accountEntity) {
        return AccountDTO.builder()
                .id(accountEntity.getId())
                .bank(accountEntity.getBank())
                .agency(accountEntity.getAgency())
                .number(accountEntity.getNumber())
                .build();
    }

    public static AccountEntity build(AccountDTO accountDTO) {
        return AccountEntity.builder()
                .id(null)
                .bank(accountDTO.getBank())
                .agency(accountDTO.getAgency())
                .number(accountDTO.getNumber())
                .build();
    }
}
