package com.example.parking.exporter.csv;

import com.example.parking.domain.ProgramExtract;
import com.example.parking.properties.AppProperties;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ExtractCsvWriter extends GenericCsvWriter<ProgramExtract> {


    private static final Integer ASSISTANT_TECHNIQUE = 5;
    private final AppProperties appProperties;
    //private final DateService dateService;

    public StringBuilder addHeader() {
        return new StringBuilder()
                .append("\"PS\"").append(DEFAULT_FIELD_SEPARATOR)
                .append("\"N° de commande\"").append(DEFAULT_FIELD_SEPARATOR)
                .append("\"Site\"").append(DEFAULT_FIELD_SEPARATOR)
                .append("\"Service\"").append(DEFAULT_FIELD_SEPARATOR)
                .append("\"Projet\"").append(DEFAULT_FIELD_SEPARATOR)
                .append("\"Contient des AIP\"").append(DEFAULT_FIELD_SEPARATOR)
                .append("\"Etat du Ps\"").append(DEFAULT_FIELD_SEPARATOR)
                .append("\"Prestataire\"").append(DEFAULT_FIELD_SEPARATOR)
                .append("\"FAS N°\"").append(DEFAULT_FIELD_SEPARATOR)
                .append("\"Libellé FAS\"").append(DEFAULT_FIELD_SEPARATOR)
                .append("\"RF\"").append(DEFAULT_FIELD_SEPARATOR)
                .append("\"OT/DL\"").append(DEFAULT_FIELD_SEPARATOR)
                .append("\"Autre\"").append(DEFAULT_FIELD_SEPARATOR)
                .append("\"Themes\"").append(DEFAULT_FIELD_SEPARATOR)
                .append("\"Sous-Themes\"").append(DEFAULT_FIELD_SEPARATOR)
                .append("\"Observable\"").append(DEFAULT_FIELD_SEPARATOR)
                .append("\"C/NC/BP\"").append(DEFAULT_FIELD_SEPARATOR)
                .append("\"Commentaire\"").append(DEFAULT_FIELD_SEPARATOR)
                .append("\"Gravité\"").append(DEFAULT_FIELD_SEPARATOR)
                .append("\"Type d'observable\"").append(DEFAULT_FIELD_SEPARATOR)
                .append("\"Libellé du Flag\"").append(DEFAULT_FIELD_SEPARATOR)
                .append("\"Date signature\"").append(DEFAULT_FIELD_SEPARATOR)
                .append("\"Surveillant\"").append(DEFAULT_FIELD_SEPARATOR)
                .append("\"Signature prestataire\"").append(DEFAULT_FIELD_SEPARATOR)
                .append("\"Ps créé par assistant technique\"").append(DEFAULT_FIELD_SEPARATOR)
                .append("\"Etat TNC FAS\"").append(DEFAULT_FIELD_SEPARATOR)
                .append("\"Photo\"").append(DEFAULT_FIELD_SEPARATOR)
                .append("\"Lien Argos\"").append(DEFAULT_FIELD_SEPARATOR)
                .append("\"FAS créée sur la tablette\"").append(DEFAULT_FIELD_SEPARATOR)
                .append("\"Date d'initialisation de la FAS\"").append(DEFAULT_FIELD_SEPARATOR)
                .append("\"Observation inadéquate\"").append(DEFAULT_FIELD_SEPARATOR)
                .append("\"Libellé du PS\"").append(DEFAULT_LINE_SEPARATOR);
    }

    public static String alternateEmptyReplaceWithDefaultValue(String data) {
        return StringUtils.isEmpty(data) ? DEFAULT_VALUE : data;
    }

    public String toCsvRow(ProgramExtract pse) {
        String aip;
        if (pse.getAip() != null) {
            aip = Boolean.TRUE.equals(pse.getAip()) ? TRUE : FALSE;
        } else {
            aip = NON_RENSEIGNE;
        }

        String signatureBy = pse.getSignatureBy() != null ? TRUE : FALSE;
        String image = pse.getImage() > 0L ? TRUE : FALSE;
        //String argosUrl = appProperties.getArgos().getUrl();
        /*String fasUrl = argosUrl.concat(pse.getIdprogram().toString()).concat("/fiches-action-surveillance/")
                .concat(pse.getFasId().toString());*/
        String fasExpected = pse.isFasExpected() ? FALSE : TRUE;
        String fasInadequate = pse.isInadequate() ? TRUE : FALSE;
        String theme = pse.getThemecode() + " - " + pse.getThemelabel();
        String subtheme = pse.getCodesubtheme() + " - " + pse.getSubthemelabel();
        return Stream.of(
                pse.getIdprogram().toString(),
                pse.getOrderCode(),
                pse.getSite(),
                pse.getServiceLabel(),
                pse.getProjectLabel(),
                aip,
                pse.getWorkFlow().getLabel(),
                pse.getPrestataire(),
                pse.getFasId().toString(),
                pse.getLibelleFas(),
                pse.getRf(),
                pse.getWorkOrder(),
                pse.getAutre(),
                theme,
                subtheme,
                pse.getLibelleObservable(),
                pse.getObservationCode(),
                alternateEmptyReplaceWithDefaultValue(pse.getCommentaire()),
                pse.getSeverity().toString(),
                pse.getAgentSupervisionSheet(),
                signatureBy,
                image,
                fasExpected,
                fasInadequate,
                pse.getPsLabel())
                .map(data -> escapeSpecialCharacters(data, true))
                .collect(Collectors.collectingAndThen(Collectors.joining(DEFAULT_FIELD_SEPARATOR), row -> row += DEFAULT_LINE_SEPARATOR));
    }


}
