package com.nerdyprogrammer.loanservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(name = "ResponseDto", description = "Schema to hold successful response information")
public class ResponseDto {

    @Schema(description = "Status code in the response")
    private String status;

    @Schema(description = "Status message in the response")
    private String statusMessage;
}
