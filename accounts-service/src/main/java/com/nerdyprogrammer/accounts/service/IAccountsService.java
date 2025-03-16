package com.nerdyprogrammer.accounts.service;

import com.nerdyprogrammer.accounts.dto.CustomerDto;

public interface IAccountsService {

    /**
     *
     * @param customerDto - CustomerDtoObject
     */
    void createAccount(CustomerDto customerDto);
}
