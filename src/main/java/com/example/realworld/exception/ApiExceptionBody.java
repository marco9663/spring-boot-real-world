package com.example.realworld.exception;

import lombok.Data;

import java.util.List;

public record ApiExceptionBody(List<String> body) {
}
