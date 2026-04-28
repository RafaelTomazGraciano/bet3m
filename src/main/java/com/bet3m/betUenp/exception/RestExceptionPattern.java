package com.bet3m.betUenp.exception;

import org.springframework.http.HttpStatus;

public record RestExceptionPattern(HttpStatus status, String message) {
}
