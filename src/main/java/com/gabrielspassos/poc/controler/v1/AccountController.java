package com.gabrielspassos.poc.controler.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabrielspassos.poc.controler.v1.request.AccountParamRequest;
import com.gabrielspassos.poc.controler.v1.request.AccountRequest;
import com.gabrielspassos.poc.controler.v1.response.AccountPageResponse;
import com.gabrielspassos.poc.controler.v1.response.AccountResponse;
import com.gabrielspassos.poc.controler.v1.response.ErrorResponse;
import com.gabrielspassos.poc.model.builder.AccountBuilder;
import com.gabrielspassos.poc.model.builder.AccountParamBuilder;
import com.gabrielspassos.poc.model.dto.AccountDTO;
import com.gabrielspassos.poc.model.dto.AccountParamDTO;
import com.gabrielspassos.poc.service.AccountService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.gabrielspassos.poc.config.PageConfiguration.PAGE;
import static com.gabrielspassos.poc.config.PageConfiguration.SIZE;

@RestController
@AllArgsConstructor
public class AccountController implements BaseVersion {

    private AccountService accountService;
    private ObjectMapper objectMapper;

    @ApiOperation(value = "Create account")
    @ApiResponses(value = {
            @ApiResponse(code = OK, message = OK_MESSAGE, response = AccountResponse.class),
            @ApiResponse(code = BAD_REQUEST, message = BAD_REQUEST_MESSAGE, response = ErrorResponse.class)
    })
    @PostMapping(value = "/accounts")
    public ResponseEntity<AccountResponse> createAccount(@RequestBody @Valid AccountRequest accountRequest) {
        AccountDTO accountDTO = AccountBuilder.build(accountRequest);
        AccountDTO savedAccount = accountService.createAccount(accountDTO);
        AccountResponse accountResponse = objectMapper.convertValue(savedAccount, AccountResponse.class);
        return ResponseEntity.ok(accountResponse);
    }

    @ApiOperation(value = "Get account by id")
    @ApiResponses(value = {
            @ApiResponse(code = OK, message = OK_MESSAGE, response = AccountResponse.class),
            @ApiResponse(code = NOT_FOUND, message = NOT_FOUND_MESSAGE, response = ErrorResponse.class)
    })
    @GetMapping(value = "/accounts/{id}")
    public ResponseEntity<AccountResponse> getAccountById(@PathVariable Long id) {
        AccountDTO accountDTO = accountService.getAccountById(id);
        AccountResponse accountResponse = objectMapper.convertValue(accountDTO, AccountResponse.class);
        return ResponseEntity.ok(accountResponse);
    }

    @ApiOperation(value = "Get accounts")
    @ApiResponses(@ApiResponse(code = OK, message = OK_MESSAGE, response = AccountPageResponse.class))
    @GetMapping("/accounts")
    public ResponseEntity<Page<AccountResponse>> getAccounts(AccountParamRequest accountParamRequest,
                                                       @RequestParam(required = false, defaultValue = PAGE) Integer page,
                                                       @RequestParam(required = false, defaultValue = SIZE) Integer size) {
        AccountParamDTO accountParamDTO = AccountParamBuilder.build(accountParamRequest);
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<AccountDTO> accountDTOPage = accountService.getAccounts(accountParamDTO, pageRequest);
        Page<AccountResponse> accountPage = accountDTOPage
                .map(accountDTO -> objectMapper.convertValue(accountDTO, AccountResponse.class));
        return ResponseEntity.ok(accountPage);
    }

}