package com.example.parking.domain;

import com.example.parking.domain.enums.ProgramWorkflowStatusEnum;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;

@Entity
public class Program {

    @Column(name = "workflow_status")
    @Convert(converter = ProgramWorkflowStatusEnumAttributeConverter.class)
    private ProgramWorkflowStatusEnum workflowStatus;

}
