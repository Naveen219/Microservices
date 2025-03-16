package com.nerdyprogrammer.accounts.mapper;

import com.nerdyprogrammer.accounts.dto.AccountDto;
import com.nerdyprogrammer.accounts.entity.Account;

public class AccountMapper {

    public static AccountDto toAccountsDto(Account accounts) {
        AccountDto accountDto = new AccountDto();
        accountDto.setAccountNumber(accounts.getAccountNumber());
        accountDto.setAccountType(accounts.getAccountType());
        accountDto.setBranchAddress(accountDto.getBranchAddress());
        return accountDto;
    }

    public static Account toAccount(AccountDto accountDto) {
        Account account = new Account();
        account.setAccountNumber(accountDto.getAccountNumber());
        account.setAccountType(accountDto.getAccountType());
        account.setBranchAddress(accountDto.getBranchAddress());
        return account;
    }
}
