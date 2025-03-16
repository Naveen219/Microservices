package com.nerdyprogrammer.accounts.controller;

import com.nerdyprogrammer.accounts.dto.CustomerDto;
import com.nerdyprogrammer.accounts.dto.ResponseDto;
import com.nerdyprogrammer.accounts.service.IAccountsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.nerdyprogrammer.accounts.constants.AccountConstants.MESSAGE_200;
import static com.nerdyprogrammer.accounts.constants.AccountConstants.STATUS_200;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class AccountController {


    private final IAccountsService accountsService;


    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto) {
        accountsService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(STATUS_200, MESSAGE_200));
    }

}
