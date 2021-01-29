package com.example.parking.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Value;

import java.time.LocalDate;

@Value
public class ActionPlanningListItemDto {

    Integer id;
    Integer actionId;

    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate startDate;
    Boolean isDone;

    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate groupStartDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate groupEndDate;
    Integer groupFrequency;
    long groupPlanningsCount;
}

