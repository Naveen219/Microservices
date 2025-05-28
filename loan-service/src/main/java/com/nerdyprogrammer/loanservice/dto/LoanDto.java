package com.nerdyprogrammer.loanservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

/**
 * @author Naveen Kumar
 * @Date 5/28/2025
 */
@Schema(name = "LoanDt", description = "Loan details")
@Data
public class LoanDto {

    @NotEmpty(message = "Mobile number cannot be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be 10 digits")
    @Schema(description = "Mobile number of the customer", example = "1234567890")
    private String mobileNumber;

    @NotEmpty(message = "Loan number cannot be null or empty")
    @Pattern(regexp = "(^$|[0-9]{12})", message = "Loan number should be 12 digits")
    private String loanNumber;

    @NotEmpty(message = "LoanType can not be a null or empty")
    @Schema(
            description = "Type of the loan", example = "Home Loan"
    )
    private String loanType;

    @Positive(message = "Total loan amount should be positive")
    @Schema(
            description = "Total loan amount", example = "10000"
    )
    private int totalLoanAmount;

    @PositiveOrZero(message = "Total amount paid should be equal or greater than zero")
    @Schema(
            description = "Total amount paid by a Customer", example = "1000"
    )
    private int amountPaid;

    @PositiveOrZero(message = "Total outstanding amount should be equal or greater than zero")
    @Schema(
            description = "Total outstanding amount against a loan", example = "90000"
    )
    private int outstandingAmount;


}
