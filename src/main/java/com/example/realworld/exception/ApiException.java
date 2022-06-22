package com.example.realworld.exception;

import lombok.Data;

@Data
public class ApiException {
    private final ApiExceptionBody errors;
}

