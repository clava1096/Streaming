package com.clava1096.musicstreaming.exceptions.Payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@AllArgsConstructor
@Getter
public class ErrorResponse {
    @JsonProperty("Error Message")
    private final String message;

    @JsonProperty("HTTP Request Status")
    private final HttpStatus httpStatus;

    @JsonProperty("Error Status Code")
    private final int statusCode;

    @JsonProperty("Request Date-Time Attempt")
    private final ZonedDateTime timeStamp;
}

