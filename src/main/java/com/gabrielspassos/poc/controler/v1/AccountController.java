package com.gabrielspassos.poc.controler.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabrielspassos.poc.controler.v1.request.AccountRequest;
import com.gabrielspassos.poc.controler.v1.response.AccountResponse;
import com.gabrielspassos.poc.model.builder.AccountBuilder;
import com.gabrielspassos.poc.service.AccountService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping(value = "/accounts")
@AllArgsConstructor
public class AccountController implements BaseVersion {

    private AccountService accountService;
    private ObjectMapper objectMapper;

    @ApiOperation(value = "Create account")
    @ApiResponses(@ApiResponse(code = OK, message = OK_MESSAGE, response = AccountResponse.class))
    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(@RequestBody AccountRequest accountRequest) {
        return Stream.of(accountRequest)
                .map(AccountBuilder::build)
                .map(accountDTO -> accountService.createAccount(accountDTO))
                .map(savedAccount -> objectMapper.convertValue(savedAccount, AccountResponse.class))
                .map(ResponseEntity::ok)
                .findFirst()
                .get();
    }

}