package com.clava1096.musicstreaming.exceptions.custom;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Getter
public class InvalidFieldException extends ApiRequestException {

    public InvalidFieldException(String message) {
        super(message);
        this.setHttpStatus(HttpStatus.BAD_REQUEST);
        this.setStatusCode(400);
        this.setTimeStamp(ZonedDateTime.now(ZoneId.of("GMT-6")));
    }
}
