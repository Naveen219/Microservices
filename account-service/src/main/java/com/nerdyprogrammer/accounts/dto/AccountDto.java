package com.nerdyprogrammer.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Account",
        description = "Schema to hold account details"
)
public class AccountDto {

    @NotEmpty(message = "Account number cannot be empty")
    @Schema(description = "Account number of the customer", example = "494680000000")
    private Long accountNumber;

    @NotEmpty(message = "Account type cannot be empty")
    @Schema(description = "Account type of the customer", example = "SAVINGS")
    private String accountType;

    @NotEmpty(message = "Branch address cannot be empty")
    @Schema(description = "branch address of the customer's account", example = "123, Main Street, New York")
    private String branchAddress;

}
