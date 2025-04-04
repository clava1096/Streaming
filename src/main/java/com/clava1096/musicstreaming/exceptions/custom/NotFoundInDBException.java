package com.clava1096.musicstreaming.exceptions.custom;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Getter
public class NotFoundInDBException extends ApiRequestException {

    public NotFoundInDBException(String message) {
        super(message);
        this.setHttpStatus(HttpStatus.NOT_FOUND);
        this.setStatusCode(404);
        this.setTimeStamp(ZonedDateTime.now(ZoneId.of("GMT-6")));
    }
}
