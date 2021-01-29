package com.example.parking.configuration;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

@MapperConfig(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD,
        unmappedTargetPolicy = ReportingPolicy.ERROR, typeConversionPolicy = ReportingPolicy.ERROR)
public interface MapStructConfig {
}
