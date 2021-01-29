package com.example.parking.domain;

import com.example.parking.domain.enums.SubthemesImportEfepEnum;
import lombok.Value;

@Value
public class Efep {

    private SubthemesImportEfepEnum subtheme;
    private String comment;
    private String note;
}
