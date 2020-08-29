package com.gabrielspassos.poc.service;

import com.gabrielspassos.poc.model.builder.AccountBuilder;
import com.gabrielspassos.poc.model.dto.AccountDTO;
import com.gabrielspassos.poc.model.entity.AccountEntity;
import com.gabrielspassos.poc.repository.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountDTO createAccount(AccountDTO accountDTO) {
        AccountEntity accountEntity = AccountBuilder.build(accountDTO);
        AccountEntity savedEntity = accountRepository.save(accountEntity);
        return AccountBuilder.build(savedEntity);
    }
}
