package com.ayo.process.service;

import com.ayo.process.helper.FindFactorHelper;
import com.ayo.process.model.ConversionDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DistanceConversionService implements IConversionService {


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
