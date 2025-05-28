package com.nerdyprogrammer.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class FieldError {
    private String field;
    private String message;
}
