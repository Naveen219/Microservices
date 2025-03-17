package com.nerdyprogrammer.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountDto {

    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be 10 digits")
    @NotEmpty(message = "Account number cannot be empty")
    private Long accountNumber;

    @NotEmpty(message = "Account type cannot be empty")
    private String accountType;

    @NotEmpty(message = "Branch address cannot be empty")
    private String branchAddress;

}
