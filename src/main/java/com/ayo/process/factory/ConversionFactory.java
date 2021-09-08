package com.ayo.process.factory;

import com.ayo.process.service.IConversionService;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import java.util.Set;

@Component
public class ConversionFactory {

    @Autowired
    ApplicationContext applicationContext;

    public IConversionService getConversionService(String conversionType) {
        Reflections reflections = new Reflections(IConversionService.class.getPackageName());
        Set<Class<? extends IConversionService>> baseClass = reflections.getSubTypesOf(IConversionService.class);
        final Optional<Class<? extends IConversionService>> foundService = baseClass.stream().filter(service -> StringUtils.startsWithIgnoreCase(service.getSimpleName(), conversionType)).findFirst();
        if(foundService.isPresent()) {
            IConversionService iConversionService = applicationContext.getBean(foundService.get());
            return iConversionService;
        }
        return null;
    }
}
