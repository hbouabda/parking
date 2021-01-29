package com.example.parking.domain.enums;

import com.example.parking.exception.FunctionalException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ProgramWorkflowStatusEnum {
    //En cours de rédaction
    WRITE_IN_PROGRESS(0, "En cours de rédaction"),
    //En cours de réalisation. Prêt à être utilisé sur le terrain.
    WORK_READY(1, "En cours de réalisation"),
    //Soldé
    ENDED(2, "Soldé"),
    //Cloturé
    CLOSED(3, "Clôturé"),
    //A contrôler
    CONTROL_REQUESTED(4, "À contrôler"),
    //A approuver
    APPROVAL_REQUESTED(5, "À approuver"),
    //Approuvé (Bibliothèque).Prêt à être utilisé.
    LIBRARY_READY(6, "Approuvé"),
    //A cloturer
    CLOSE_REQUESTED(7, "À clôturer"),
    //Suppression à contrôler
    LIBRARY_DELETE_CONTROL_REQUESTED(8, "Suppression à clôturer"),
    //Suppression à approuver
    LIBRARY_DELETE_APPROVAL_REQUESTED(9, "Suppression à approuver"),
    //Supprimé
    LIBRARY_DELETED(10, "Supprimé");

    private final int value;
    @Getter
    private final String label;


    public static Collection<ProgramWorkflowStatusEnum> fromIntegers(Collection<Integer> workflowstatuses) {
        if (workflowstatuses == null) {
            return Collections.emptyList();
        }

        return workflowstatuses.stream()
                .map(ProgramWorkflowStatusEnum::fromValue)
                .collect(Collectors.toSet());
    }


    public int getValue() {
        return value;
    }

    private static final Map<Integer, ProgramWorkflowStatusEnum> BY_VALUE = new HashMap<>();

    static {
        for (ProgramWorkflowStatusEnum v : values()) {
            BY_VALUE.put(v.getValue(), v);
        }
    }


    public static ProgramWorkflowStatusEnum fromValue(Integer value) {
        if (value == null) {
            return null;
        }
        ProgramWorkflowStatusEnum result = BY_VALUE.get(value);
        if (result == null) {
            String className = ProgramWorkflowStatusEnum.class.getSimpleName();
            throw new FunctionalException(FunctionalException.FunctionalExceptionType.ENUM_FOR_VALUE_UNKNOWN, new Object[]{value, className});
        }
        return result;
    }
}

