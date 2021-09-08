package com.ayo.process.controller;

import com.ayo.process.exception.BusinessException;
import com.ayo.process.factory.ConversionFactory;
import com.ayo.process.model.APIError;
import com.ayo.process.model.ConversionDetails;
import com.ayo.process.service.IConversionService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@Slf4j
public class ConversionController {

    @Autowired
    ConversionFactory conversionFactory;

    @PostMapping("/convert")
    @ApiOperation(value = "Converts Metric to imperial and vice versa",
            notes = "This operation converts the metric to imperial and vice versa.", response = ConversionDetails.class)
    public ResponseEntity<?> convertData(@Valid @RequestBody ConversionDetails conversionDetails) {
        try {
            IConversionService conversionService = conversionFactory.getConversionService(conversionDetails.getConverstionType().name());
            ConversionDetails conversionResponse = conversionService.convert(conversionDetails);
            return ResponseEntity.ok(conversionResponse);
        } catch (BusinessException e) {
            log.error("Exception Occurred : ", e);
            APIError apiError = new APIError(e.getMessage(), e.getErrorCode());
            return ResponseEntity.status(e.getHttpStatus()).body(apiError);
        } catch(Exception e) {
            log.info("Error: ",e);
            APIError apiError = new APIError(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);
        }
    }
}
