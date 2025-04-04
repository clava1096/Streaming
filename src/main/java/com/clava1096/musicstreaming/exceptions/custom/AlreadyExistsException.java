package com.clava1096.musicstreaming.exceptions.custom;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Getter
public class AlreadyExistsException extends ApiRequestException {
    public AlreadyExistsException(String message) {
        super(message);
        this.setHttpStatus(HttpStatus.CONFLICT);
        this.setStatusCode(409);
        this.setTimeStamp(ZonedDateTime.now(ZoneId.of("GMT-6")));
    }
}
