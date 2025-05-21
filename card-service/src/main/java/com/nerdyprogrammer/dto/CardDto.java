package com.nerdyprogrammer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Schema(name = "Card", description = "Schema to hold Card information")
@Data
public class CardDto {

    @NotEmpty(message = "Mobile number cannot be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be 10 digits")
    @Schema(description = "Mobile number of the customer", example = "1234567890")
    private String mobileNumber;

    @NotEmpty(message = "Card number cannot be null or empty")
    @Pattern(regexp = "(^$|[0-9]{12})", message = "Card number should be 12 digits")
    @Schema(description = "Card number of the customer", example = "123456789012")
    private String cardNumber;

    @NotEmpty(message = "Card type cannot be null or empty")
    @Schema(description = "Card type of the customer", example = "Credit")
    private String cardType;


    @Positive(message = "Total limit should be positive")
    @Schema(description = "Total limit of the card", example = "10000")
    private int totalLimit;

    @PositiveOrZero(message = "Total amount used should be equal or greater than zero")
    @Schema(description = "Total amount used by a Customer", example = "1000")
    private int amountUsed;

    @PositiveOrZero(message = "Total available amount should be equal or greater than zero")
    @Schema(description = "Total available amount against a card", example = "90000")
    private int availableAmount;

}

