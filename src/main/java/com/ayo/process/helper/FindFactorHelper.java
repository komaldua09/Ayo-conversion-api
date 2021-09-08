package com.ayo.process.helper;

import com.ayo.process.constants.SystemConstants;
import com.ayo.process.enums.ErrorCodes;
import com.ayo.process.exception.BusinessException;
import com.ayo.process.model.ConversionDetails;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Optional;

import static com.ayo.process.exception.BusinessException.businessException;

@Component
public class FindFactorHelper {

    public Double getFactorValue(ConversionDetails conversionDetails, String factorName) {
        Class<SystemConstants> systemConstantsClass = SystemConstants.class;
        Field[] fields = systemConstantsClass.getFields();
        try {
            Optional<Field> foundField = Arrays.stream(fields).filter(field -> field.getName().equalsIgnoreCase(factorName)).findFirst();
            if (foundField.isPresent()) {
                double factor = 0;
                factor = foundField.get().getDouble(null);
                return factor;
            }
        } catch (IllegalAccessException e) {
            throw businessException(ErrorCodes.INVALID_INPUT_PARAMETERS);
        }
        throw businessException(ErrorCodes.INVALID_INPUT_PARAMETERS);
    }
}
