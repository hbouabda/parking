package com.example.parking.importer.csv;

import com.example.parking.domain.Efep;
import com.example.parking.domain.enums.SubthemesImportEfepEnum;
import com.example.parking.exception.FunctionalException;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EfepFileCsvReader extends GenericCsvReader {

    private static final String NOTE_A = "A";
    private static final String NOTE_C = "C";
    private static final String NOTE_D = "D";
    private static final String COMMENT = "Commentaire ";
    private static final String NOTE = "Note ";

    /**
     * Lecture d'un fichier efep pour en extraire le contenu
     *
     * @return List de {@link Efep} de la fep notés A, C ou D
     */

    @Override
    public List<Efep> parse(byte[] fileContent) throws IOException {

        CSVParser parser = this.readCsvFile(fileContent);
        Map<String, Integer> header = this.getHeader(parser);

        SubthemesImportEfepEnum[] subthemes = SubthemesImportEfepEnum.values();

        for (SubthemesImportEfepEnum subtheme : subthemes) {
            if (!header.containsKey(this.getNoteColumn(subtheme)) || !header.containsKey(this.getCommentColumn(subtheme))) {
                throw new FunctionalException(FunctionalException.FunctionalExceptionType.INVALID_CSV_FORMAT);
            }
        }

        List<Efep> efepdtos = new ArrayList<>();
        for (CSVRecord line : parser) {
            if (line.size() != header.size()) {
                throw new FunctionalException(FunctionalException.FunctionalExceptionType.INVALID_CSV_FORMAT);
            }
            for (SubthemesImportEfepEnum subtheme : subthemes) {
                String note = line.get(this.getNoteColumn(subtheme));
                String comment = line.get(this.getCommentColumn(subtheme));

                if (NOTE_A.equals(note) || NOTE_C.equals(note) || NOTE_D.equals(note)) {
                    efepdtos.add(new Efep(subtheme, comment, note));
                }
            }
        }

        return efepdtos;
    }

    /**
     * Obtient le nom de la colonne de la note dans l'import e-FEP
     *
     * @return Nom de la colonne
     */
    public String getNoteColumn(SubthemesImportEfepEnum subthemes) {
        StringBuilder note = new StringBuilder();
        note.append(NOTE);
        note.append(subthemes.getValue());
        return note.toString();
    }

    /**
     * Obtient le nom de la colonne du commentaire de la note dans l'import e-FEP
     *
     * @return Nom de la colonne
     */
    public String getCommentColumn(SubthemesImportEfepEnum subthemes) {

        StringBuilder comment = new StringBuilder();
        comment.append(COMMENT);
        comment.append(subthemes.getValue());
        return comment.toString();
    }
}

