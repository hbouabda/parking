package com.example.parking.exporter.csv;

import org.apache.commons.lang3.StringUtils;

import java.text.NumberFormat;
import java.util.Collection;
import java.util.Locale;

public abstract class GenericCsvWriter<T> {

    // Valeurs affichées pour un boolean
    public static final String TRUE = "OUI";
    public static final String FALSE = "NON";
    public static final String NON_RENSEIGNE = "NON RENSEIGNE";
    protected static final String DEFAULT_FIELD_SEPARATOR = ";";
    protected static final String DEFAULT_LINE_SEPARATOR = "\n";
    protected static final String DEFAULT_VALUE = "N/A";
    protected static final String DEFAULT_DASH = " - ";
    protected static final String LINE_BREAK = "[\r\n]+";

    // Types d'observables
    public static final String LOC = "LOC";
    public static final String NAT = "NAT";
    public static final String MOB = "MOB";
    public static final String SPE = "SPE";


    // Libellé du site National
    public static final String NATIONAL_PLANT_LABEL = "National";

    public String toCsv(Collection<T> objectList) {
        StringBuilder sb = addHeader();
        objectList.stream()
                .map(this::toCsvRow)
                .forEach(sb::append);
        return sb.toString() + "\n";
    }

    protected abstract StringBuilder addHeader();

    protected abstract String toCsvRow(T object);

    protected String escapeSpecialCharacters(String data, boolean emptyForNull) {
        if (data == null) {
            return emptyForNull ? StringUtils.EMPTY : DEFAULT_VALUE;
        } else {
            return "\"" + data.replace("\"", "'")
                    .replace(DEFAULT_FIELD_SEPARATOR, ",")
                    .replaceAll(LINE_BREAK, StringUtils.EMPTY) + "\"";
        }
    }

    public static String formatNumber(Number n) {
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.FRENCH);
        // désactive les espaces comme séparateur de milliers sinon Excel les traite
        // comme du texte et pas des nombres
        nf.setGroupingUsed(false);

        return nf.format(n);
    }
}