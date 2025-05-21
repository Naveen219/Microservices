package com.nerdyprogrammer.dto;

import lombok.Data;

import java.util.List;

@Data
public class ValidationErrorResponse {
    private List<FieldError> errors;
    private String errorCode;
    private String errorTime;
    private String apiPath;
}
