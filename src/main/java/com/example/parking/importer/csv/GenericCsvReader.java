package com.example.parking.importer.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class GenericCsvReader<T> {

    private static final char SEPARATOR = ';';
    private static final String DEFAULT_ENCODING = "Cp1252";

    protected List<Map<String, String>> parseCsvFile(CSVParser csvParser) {

        List<Map<String, String>> results = new ArrayList<>();
        for (CSVRecord line : csvParser) {
            results.add((line.toMap()));
        }

        return results;
    }

    protected Map<String, Integer> getHeader(CSVParser csvParser) {
        return csvParser.getHeaderMap();

    }

    /**
     * <p>
     * Ouvre le fichier, lit toutes les lignes et map son contenu
     * </p>
     *
     * @return CSVParser
     */

    protected CSVParser readCsvFile(byte[] fileContent) throws IOException {

        String csvString = new String(fileContent, DEFAULT_ENCODING);
        CSVFormat csvFormat = CSVFormat.EXCEL.withDelimiter(SEPARATOR).withHeader();
        return CSVParser.parse(csvString, csvFormat);
    }

    public abstract List<T> parse(byte[] fileContent) throws IOException;
}
