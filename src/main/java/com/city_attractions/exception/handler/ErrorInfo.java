package com.city_attractions.exception.handler;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ErrorInfo {
    private final ZonedDateTime timestamp;
    private final Integer status;
    private final String error;
    private final String message;
    private final String path;

    public ErrorInfo(String path, String message, HttpStatus status) {
        this.path = path;
        this.message = message;
        this.status = status.value();
        this.error = status.name();
        this.timestamp = ZonedDateTime.now();
    }

    public String getPath() {
        return path;
    }

    public String getMessage() {
        return message;
    }

    public Integer getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }
}