package com.example.parking.exception;

public class FunctionalException extends ApplicationException {

    private static final long serialVersionUID = 5477619054099558741L;

    public enum FunctionalExceptionType implements ExceptionType {
        /**
         * You should define your custom functional exception in this enum
         */
        USER_GUIDE_DELETION_IMPOSSIBLE(ErrorMessage.USER_GUIDE_DELETION_IMPOSSIBLE),
        FILE_UPLOAD_ERROR(ErrorMessage.FILE_UPLOAD_ERROR),
        FILE_DELETE_ERROR(ErrorMessage.FILE_DELETE_ERROR),
        FILE_EXTENSION_NOT_ALLOWED(ErrorMessage.FILE_EXTENSION_NOT_ALLOWED),
        FILE_DOWNLOAD_ERROR(ErrorMessage.FILE_DOWNLOAD_ERROR),
        ENUM_FOR_VALUE_UNKNOWN(ErrorMessage.ENUM_FOR_VALUE_UNKNOWN),
        INVALID_CSV_FORMAT(ErrorMessage.INVALID_CSV_FORMAT);

        private final String messageKey;
        private final String titleKey;
        private final String messageCause;

        FunctionalExceptionType(ErrorMessage errorMessage) {
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

    public FunctionalException(FunctionalExceptionType type) {
        super(type);
    }

    public FunctionalException(FunctionalExceptionType type, Throwable cause) {
        super(type, cause);
    }

    public FunctionalException(FunctionalExceptionType type, Object... valueParams) {
        super(type, valueParams);
    }

}
