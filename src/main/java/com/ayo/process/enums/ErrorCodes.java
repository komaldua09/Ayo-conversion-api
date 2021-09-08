package com.ayo.process.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCodes {

    INVALID_INPUT_PARAMETERS("5000", "The input parameters provided are invalid.", HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR("3001", "An internal server error occurred and processing could not be completed.", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String code;
    private final String description;
    private final HttpStatus httpStatus;

}
