package com.clava1096.musicstreaming.exceptions.Handler;

import com.clava1096.musicstreaming.exceptions.Payload.ApiExceptionResponse;
import com.clava1096.musicstreaming.exceptions.Payload.ErrorResponse;
import com.clava1096.musicstreaming.exceptions.custom.ApiRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiRequestExceptionHandler {

    @ExceptionHandler({ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestExceptions(ApiRequestException rEx) {

        //create payload containing ex details
        ErrorResponse errorResponse = new ErrorResponse(
                rEx.getMessage(),
                rEx.getHttpStatus(),
                rEx.getStatusCode(),
                rEx.getTimeStamp()
        );

        //response entity
        return new ResponseEntity<>(errorResponse, rEx.getHttpStatus());
    }
}
