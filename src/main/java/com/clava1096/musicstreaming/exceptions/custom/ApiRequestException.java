package com.clava1096.musicstreaming.exceptions.custom;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Setter
@Getter
public class ApiRequestException extends RuntimeException {

    public ApiRequestException(String message) {
        super(message);
    }

    private HttpStatus httpStatus;

    private int statusCode;

    private ZonedDateTime timeStamp;
}

