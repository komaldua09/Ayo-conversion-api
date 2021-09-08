package com.ayo.process.service;

import com.ayo.process.constants.SystemConstants;
import com.ayo.process.helper.FindFactorHelper;
import com.ayo.process.model.ConversionDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Optional;

@Service
public class VolumeConversionService implements IConversionService {


    @Autowired
    FindFactorHelper findFactorHelper;

    @Override
    public ConversionDetails convert(ConversionDetails conversionDetails) {
        var factorName = conversionDetails.getConvertFrom().name()+"_TO_"+ conversionDetails.getConvertTo().name();
        var factor = findFactorHelper.getFactorValue(conversionDetails, factorName);
        conversionDetails.setConvertedValue(conversionDetails.getValueToConvert() * factor);
        return conversionDetails;
    }
}
