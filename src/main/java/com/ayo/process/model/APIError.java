package com.ayo.process.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class APIError {

    private String message;
    private String errorCode;

}
