package com.example.parking.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class ActionPlanningListItem {

    Integer id;
    Integer actionId;
    LocalDate startDate;
    Boolean isDone;
    LocalDate groupStartDate;
    LocalDate groupEndDate;
    Integer groupFrequency;
    long groupPlanningsCount;


}