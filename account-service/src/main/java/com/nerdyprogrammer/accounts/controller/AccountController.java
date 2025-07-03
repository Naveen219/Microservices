package com.nerdyprogrammer.accounts.controller;

import com.nerdyprogrammer.accounts.constants.AccountConstants;
import com.nerdyprogrammer.accounts.dto.CustomerDto;
import com.nerdyprogrammer.accounts.dto.ErrorResponseDto;
import com.nerdyprogrammer.accounts.dto.ResponseDto;
import com.nerdyprogrammer.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.nerdyprogrammer.accounts.constants.AccountConstants.MESSAGE_200;
import static com.nerdyprogrammer.accounts.constants.AccountConstants.STATUS_200;

// Rest controller is a combination of @Controller and @ResponseBody annotations
//Note : ResponseBody annotation returns data in the form of JSON
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@Tag(name = "CRUD REST API for Accounts Microservice", description = "CRUD REST API for Accounts")
@Slf4j
public class AccountController {


    @Value("${build.version}")
    private String build_version;

    private final IAccountsService accountsService;

    @Autowired
    public AccountController(IAccountsService accountsService) {
        this.accountsService = accountsService;
    }

    @PostMapping("/create")
    @Operation(summary = "Create a new account", description = "Create a new account")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        accountsService.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(STATUS_200, MESSAGE_200));
    }

    /**
     * @param mobileNumber
     * @return AccountDetails for a given mobile number
     */
    @GetMapping("/get")
    @Operation(summary = "Get account details", description = "Get account details for a given mobile number")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    public ResponseEntity<CustomerDto> getAccountDetails(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be 10 digits") String mobileNumber) {
        CustomerDto response = accountsService.getAccountDetails(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * @param customerDto
     * @return updates customer details and accountDetails for a given account number
     */

    @PutMapping("/update")
    @Operation(summary = "Update account details", description = "Update account details for a given mobile number")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "417", description = "Expectation failed"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    // by default errorResponseDto will not be displayed in the swagger as ErrorResponse is returned
                    // by GlobalExceptionHandler rather than rest controllers
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))}

    )
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = accountsService.updateAccount(customerDto);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(STATUS_200, MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(AccountConstants.STATUS_417, AccountConstants.MESSAGE_417_UPDATE));
        }

    }

    @DeleteMapping("/delete")
    @Operation(summary = "delete an account ", description = "delete an account for a given mobile number")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "417", description = "Expectation failed"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be 10 digits") String mobileNumber) {
        boolean isUpdated = accountsService.deleteAccount(mobileNumber);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(STATUS_200, MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(AccountConstants.STATUS_417, AccountConstants.MESSAGE_417_DELETE));
        }

    }

    @GetMapping("/build-info")
    @Operation(summary = "Get Build Version", description = "Get Build version of the application")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    public ResponseEntity<String> getBuildVersion() {
        log.info("Build version of the application : {}", build_version);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(build_version);
    }
}
