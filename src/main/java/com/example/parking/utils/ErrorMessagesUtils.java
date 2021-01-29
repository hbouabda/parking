package com.example.parking.utils;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.Locale;
import java.util.ResourceBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.MissingResourceException;

public final class ErrorMessagesUtils {

    private static final ResourceBundle ERROR_RESOURCE_BUNDLE = ResourceBundle.getBundle(
            "messages_erreur_fr_FR", Locale.FRANCE);

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorMessagesUtils.class);

    private ErrorMessagesUtils() {
        // private constructor to hide the implicit public one
    }

    public static String getError(String errorMessageKey) {
        try {
            return MessageFormat.format(ERROR_RESOURCE_BUNDLE.getString(errorMessageKey), Collections.emptyList());
        } catch (MissingResourceException e) {
            LOGGER.error(e.getMessage(), e);
            // Robustesse : message non trouvé : on veut quand même remonter les informations de l'erreur initiale
            return errorMessageKey;
        }
    }

    public static String getError(String errorMessageKey, Object... params) {
        try {
            return MessageFormat.format(ERROR_RESOURCE_BUNDLE.getString(errorMessageKey), params);
        } catch (MissingResourceException e) {
            LOGGER.error(e.getMessage(), e);
            // Robustesse : message non trouvé : on veut quand même remonter les informations de l'erreur initiale
            StringBuilder message = new StringBuilder(errorMessageKey);
            if (params != null) {
                for (Object param : params) {
                    message.append(" ").append(param);
                }
            }
            return message.toString();
        }
    }

}
