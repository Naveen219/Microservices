package com.nerdyprogrammer.accounts.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class CustomerDto {

    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30")
    private String name;

    @NotEmpty(message = "Email address cannot be empty")
    @Email(message = "Email address should be valid")
    private String email;

    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be 10 digits")
    @NotEmpty(message = "Mobile number cannot be empty")
    private String mobileNumber;

    private AccountDto accountDto;
}
