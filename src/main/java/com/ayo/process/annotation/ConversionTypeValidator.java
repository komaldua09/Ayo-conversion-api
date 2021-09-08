package com.ayo.process.annotation;

import com.ayo.process.enums.ConversionUnits;
import com.ayo.process.model.APIError;
import com.ayo.process.model.ConversionDetails;
import org.springframework.http.HttpStatus;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ConversionTypeValidator implements ConstraintValidator<ConversionTypeIsValid, ConversionDetails> {

    @Override
    public void initialize(ConversionTypeIsValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(ConversionDetails conversionDetails, ConstraintValidatorContext context) {
        if (conversionDetails == null)
            return false;

        if (conversionDetails.getConvertTo().equals(conversionDetails.getConvertFrom()))
            return false;

        switch (conversionDetails.getConverstionType()) {
            case AREA:
                return (conversionDetails.getConvertTo().equals(ConversionUnits.SQ_KM) && conversionDetails.getConvertFrom().equals(ConversionUnits.SQ_MILE)) ||
                        (conversionDetails.getConvertTo().equals(ConversionUnits.SQ_MILE) && conversionDetails.getConvertFrom().equals(ConversionUnits.SQ_KM));
            case DISTANCE:
                return (conversionDetails.getConvertTo().equals(ConversionUnits.KM) && conversionDetails.getConvertFrom().equals(ConversionUnits.MILE)) ||
                        (conversionDetails.getConvertTo().equals(ConversionUnits.MILE) && conversionDetails.getConvertFrom().equals(ConversionUnits.KM));
            case TEMPERATURE:
                return (conversionDetails.getConvertTo().equals(ConversionUnits.CELCIUS) && conversionDetails.getConvertFrom().equals(ConversionUnits.FAHRENHEIT)) ||
                        (conversionDetails.getConvertTo().equals(ConversionUnits.FAHRENHEIT) && conversionDetails.getConvertFrom().equals(ConversionUnits.CELCIUS));
            case WEIGHT:
                return (conversionDetails.getConvertTo().equals(ConversionUnits.POUND) && conversionDetails.getConvertFrom().equals(ConversionUnits.KG)) ||
                        (conversionDetails.getConvertTo().equals(ConversionUnits.KG) && conversionDetails.getConvertFrom().equals(ConversionUnits.POUND));
            case VOLUME:
                return (conversionDetails.getConvertTo().equals(ConversionUnits.GALLON) && conversionDetails.getConvertFrom().equals(ConversionUnits.LITRE)) ||
                        (conversionDetails.getConvertTo().equals(ConversionUnits.LITRE) && conversionDetails.getConvertFrom().equals(ConversionUnits.GALLON));
            default:
                return false;
        }
    }
}
