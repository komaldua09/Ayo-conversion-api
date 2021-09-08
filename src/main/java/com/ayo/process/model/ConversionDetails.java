package com.ayo.process.model;

import com.ayo.process.annotation.ConversionTypeIsValid;
import com.ayo.process.enums.ConversionType;
import com.ayo.process.enums.ConversionUnits;
import lombok.Builder;
import lombok.Data;

@Data
@ConversionTypeIsValid(message="The Requested payload is invalid")
@Builder
public class ConversionDetails {

    private ConversionType converstionType;
    private ConversionUnits convertTo;
    private ConversionUnits convertFrom;
    private double valueToConvert;
    private double convertedValue;
}
