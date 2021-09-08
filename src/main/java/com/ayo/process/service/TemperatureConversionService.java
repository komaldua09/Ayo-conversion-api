package com.ayo.process.service;

import com.ayo.process.constants.SystemConstants;
import com.ayo.process.enums.ConversionUnits;
import com.ayo.process.enums.ErrorCodes;
import com.ayo.process.helper.FindFactorHelper;
import com.ayo.process.model.ConversionDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Optional;

import static com.ayo.process.exception.BusinessException.businessException;

@Service
public class TemperatureConversionService implements IConversionService {

    @Autowired
    FindFactorHelper findFactorHelper;

    @Override
    public ConversionDetails convert(ConversionDetails conversionDetails) {
        var factorName = conversionDetails.getConvertFrom().name()+"_TO_"+ conversionDetails.getConvertTo().name();
        var factor = findFactorHelper.getFactorValue(conversionDetails, factorName);
        if(conversionDetails.getConvertFrom().equals(ConversionUnits.CELCIUS)) {
            var result = calculateFahrenheit(conversionDetails.getValueToConvert(), factor);
            conversionDetails.setConvertedValue(result);

        } else if(conversionDetails.getConvertFrom().equals(ConversionUnits.FAHRENHEIT)) {
            var result = calculateCelsius(conversionDetails.getValueToConvert(), factor);
            conversionDetails.setConvertedValue(result);
        } else {
            throw businessException(ErrorCodes.INVALID_INPUT_PARAMETERS);
        }
        return conversionDetails;
    }

    public static double calculateCelsius(double fahrenheit, double factor) {
        double celsius;
        celsius = (fahrenheit - 32) * factor;
        return celsius;
    }

    public static double calculateFahrenheit(double celsius, double factor) {
        double fahrenheit;
        fahrenheit = celsius * factor + 32;
        return fahrenheit;
    }
}
