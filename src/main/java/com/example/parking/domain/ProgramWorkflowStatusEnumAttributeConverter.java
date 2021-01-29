package com.example.parking.domain;

import com.example.parking.domain.enums.ProgramWorkflowStatusEnum;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;

@Component
public class ProgramWorkflowStatusEnumAttributeConverter implements AttributeConverter<ProgramWorkflowStatusEnum, Integer> {
    @Override
    public Integer convertToDatabaseColumn(ProgramWorkflowStatusEnum programWorkflowStatusEnum) {
        return programWorkflowStatusEnum == null ? null : programWorkflowStatusEnum.getValue();
    }

    @Override
    public ProgramWorkflowStatusEnum convertToEntityAttribute(Integer i) {
        return ProgramWorkflowStatusEnum.fromValue(i);
    }
}
