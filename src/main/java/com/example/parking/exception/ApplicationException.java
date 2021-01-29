package com.example.parking.exception;

import com.example.parking.utils.ErrorMessagesUtils;

import java.text.MessageFormat;
import java.util.Optional;

public abstract class ApplicationException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 7144041535587769114L;

    private final String messageKey;

    private final String titleKey;

    /**
     * Create an application exception with exception type fields
     *
     * @param type exception type
     */
    protected ApplicationException(ExceptionType type) {
        this(type, type.getMessageCauseKey());
    }

    /**
     * Create an application exception and format the messageCause, messageKey and
     * titleKey fields with the value and key parameters
     *
     * @param type        exception type
     * @param valueParams params value for messageCause field
     * @param keyParams   optionnal key params to format the message and title key
     */
    protected ApplicationException(ExceptionType type, Object[] valueParams, Object... keyParams) {
        super(ErrorMessagesUtils.getError(type.getMessageCauseKey(), valueParams));
        this.messageKey = MessageFormat.format(type.getMessageKey(), keyParams);
        this.titleKey = MessageFormat.format(type.getTitleKey(), keyParams);
    }

    /**
     * Create an application exception with a specific cause exception.
     *
     * @param type  exception type
     * @param cause cause exception
     */
    protected ApplicationException(ExceptionType type, Throwable cause) {
        super(getMessage(type, cause), cause);
        this.messageKey = type.getMessageKey();
        this.titleKey = type.getTitleKey();
    }

    /**
     * Create an application exception and format the messageKey and titleKey
     * fields with the key parameters
     *
     * @param type      exception type
     * @param keyParams key parameters to format the message and title key
     */
    protected ApplicationException(ExceptionType type, String message, Throwable cause, Object... keyParams) {
        super(message, cause);
        this.messageKey = MessageFormat.format(type.getMessageKey(), keyParams);
        this.titleKey = MessageFormat.format(type.getTitleKey(), keyParams);
    }

    /**
     * Create an application exception with a specific messageKey exception
     *
     * @param type       exception type
     * @param messageKey messageKey exception
     */
    protected ApplicationException(ExceptionType type, String messageKey) {
        super(ErrorMessagesUtils.getError(messageKey));
        this.messageKey = type.getMessageKey();
        this.titleKey = type.getTitleKey();
    }

    private static String getMessage(ExceptionType type, Throwable cause) {
        Optional<String> message;
        message = Optional.ofNullable(type.getMessageCauseKey())
                .map(ErrorMessagesUtils::getError);

        if (!message.isPresent()) {
            message = Optional.ofNullable(cause)
                    .map(Throwable::getMessage);
        }

        return message.orElse(null);
    }

    public String getMessageKey() {
        return messageKey;
    }

    public String getTitleKey() {
        return titleKey;
    }

}
