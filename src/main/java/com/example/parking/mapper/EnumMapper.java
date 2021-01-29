package com.example.parking.mapper;

import com.example.parking.domain.enums.ProgramWorkflowStatusEnum;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface EnumMapper {
    default Integer mapProgramWorkflowStatusEnum(ProgramWorkflowStatusEnum statusEnum) {
        return Optional.ofNullable(statusEnum)
                .map(ProgramWorkflowStatusEnum::getValue)
                .orElse(null);
    }
}
