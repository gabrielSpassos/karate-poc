package com.gabrielspassos.poc.service;

import com.gabrielspassos.poc.exception.AccountAlreadyExistentException;
import com.gabrielspassos.poc.exception.AccountNotFoundException;
import com.gabrielspassos.poc.model.builder.AccountBuilder;
import com.gabrielspassos.poc.model.builder.AccountParamBuilder;
import com.gabrielspassos.poc.model.dto.AccountDTO;
import com.gabrielspassos.poc.model.dto.AccountParamDTO;
import com.gabrielspassos.poc.model.entity.AccountEntity;
import com.gabrielspassos.poc.repository.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.gabrielspassos.poc.config.PageConfiguration.DEFAULT_PAGE;

@Slf4j
@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountDTO createAccount(AccountDTO accountDTO) {
        AccountParamDTO accountParamDTO = AccountParamBuilder.build(accountDTO);
        Page<AccountDTO> accountsPage = getAccounts(accountParamDTO, DEFAULT_PAGE);

        if (accountsPage.hasContent()) {
            throw new AccountAlreadyExistentException();
        }

        AccountEntity accountEntity = AccountBuilder.build(accountDTO);
        AccountEntity savedEntity = accountRepository.save(accountEntity);
        return AccountBuilder.build(savedEntity);
    }

    public AccountDTO getAccountById(Long id) {
        return accountRepository.findById(id)
                .map(AccountBuilder::build)
                .orElseThrow(AccountNotFoundException::new);
    }

    public Page<AccountDTO> getAccounts(AccountParamDTO accountParamDTO, Pageable page) {
        return accountRepository.search(accountParamDTO, page)
                .map(AccountBuilder::build);
    }
}
