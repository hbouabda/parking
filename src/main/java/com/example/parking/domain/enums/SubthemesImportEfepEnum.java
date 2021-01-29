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

/**
 * Enum pour les sous-thèmes utilisé dans la fonctionnalité d'import efep
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SubthemesImportEfepEnum {


    SUBTHEME_1_1(1, "1.1"),

    SUBTHEME_1_2(2, "1.2"),

    SUBTHEME_1_3(3, "1.3"),

    SUBTHEME_2_1(4, "2.1"),

    SUBTHEME_2_2(5, "2.2"),

    SUBTHEME_2_3(6, "2.3"),

    SUBTHEME_2_4(7, "2.4"),

    SUBTHEME_2_5(8, "2.5"),

    SUBTHEME_3_1(9, "3.1"),

    SUBTHEME_3_2(10, "3.2"),

    SUBTHEME_3_3(11, "3.3"),

    SUBTHEME_3_4(12, "3.4"),

    SUBTHEME_3_5(13, "3.5"),

    SUBTHEME_3_6(14, "3.6"),

    SUBTHEME_3_7(15, "3.7"),

    SUBTHEME_3_8(16, "3.8"),

    SUBTHEME_4A_1(17, "4A.1"),

    SUBTHEME_4A_2(18, "4A.2"),

    SUBTHEME_4B_1(19, "4B.1"),

    SUBTHEME_4B_2(20, "4B.2"),

    SUBTHEME_5_1(21, "5.1"),

    SUBTHEME_5_2(22, "5.2"),

    SUBTHEME_6_1(23, "6.1"),

    SUBTHEME_6_2(24, "6.2"),

    SUBTHEME_6_3(25, "6.3"),

    SUBTHEME_6_4(26, "6.4"),

    SUBTHEME_7_1(27, "7.1"),

    SUBTHEME_7_2(28, "7.2");


    private final int value;
    @Getter
    private final String label;


    public static Collection<SubthemesImportEfepEnum> fromIntegers(Collection<Integer> subthemesEfep) {
        if (subthemesEfep == null) {
            return Collections.emptyList();
        }

        return subthemesEfep.stream()
                .map(SubthemesImportEfepEnum::fromValue)
                .collect(Collectors.toSet());
    }


    public int getValue() {
        return value;
    }

    private static final Map<Integer, SubthemesImportEfepEnum> BY_VALUE = new HashMap<>();

    static {
        for (SubthemesImportEfepEnum v : values()) {
            BY_VALUE.put(v.getValue(), v);
        }
    }


    public static SubthemesImportEfepEnum fromValue(Integer value) {
        if (value == null) {
            return null;
        }
        SubthemesImportEfepEnum result = BY_VALUE.get(value);
        if (result == null) {
            String className = SubthemesImportEfepEnum.class.getSimpleName();
            throw new FunctionalException(FunctionalException.FunctionalExceptionType.ENUM_FOR_VALUE_UNKNOWN, new Object[]{value, className});
        }
        return result;
    }


}

