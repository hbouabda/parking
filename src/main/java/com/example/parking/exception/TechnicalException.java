package com.example.parking.exception;

public class TechnicalException extends ApplicationException {

    private static final long serialVersionUID = 5477619054099558741L;

    public TechnicalException(TechnicalExceptionType type) {
        super(type);
    }

    public TechnicalException(TechnicalExceptionType type, Throwable cause) {
        super(type, cause);
    }

    public TechnicalException(TechnicalExceptionType type, String message, Throwable cause) {
        super(type, message, cause);
    }

    public TechnicalException(TechnicalExceptionType type, Object... params) {
        super(type, params);
    }

    public enum TechnicalExceptionType implements ExceptionType {
        DEFAULT_INTERNAL_SERVER_ERROR(ErrorMessage.DEFAULT_INTERNAL_SERVER_ERROR);


        private final String messageKey;
        private final String titleKey;
        private final String messageCause;

        TechnicalExceptionType(ErrorMessage errorMessage) {
            this.messageKey = errorMessage.getMessageKey();
            this.titleKey = errorMessage.getTitleKey();
            this.messageCause = errorMessage.getMessageCauseKey();
        }

        @Override
        public String getTitleKey() {
            return titleKey;
        }

        @Override
        public String getMessageKey() {
            return messageKey;
        }

        @Override
        public String getMessageCauseKey() {
            return messageCause;
        }
    }
}
