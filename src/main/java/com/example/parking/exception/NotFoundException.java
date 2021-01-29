package com.example.parking.exception;

public class NotFoundException extends ApplicationException {
    private static final long serialVersionUID = 5477619054099558741L;

    public NotFoundException(NotFoundExceptionType type) {
        super(type);
    }

    public NotFoundException(NotFoundExceptionType type, Throwable cause) {
        super(type, cause);
    }

    public NotFoundException(NotFoundExceptionType type, String message, Throwable cause) {
        super(type, message, cause);
    }

    public enum NotFoundExceptionType implements ExceptionType {

        USER_GUIDE_NOT_FOUND(ErrorMessage.USER_GUIDE_NOT_FOUND),
        FILE_NOT_FOUND(ErrorMessage.USER_GUIDE_NOT_FOUND);

        private final String messageKey;
        private final String titleKey;
        private final String messageCause;

        NotFoundExceptionType(ErrorMessage errorMessage) {
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

    public NotFoundException(NotFoundExceptionType type, String message, Throwable cause, Object... keyParams) {
        super(type, message, cause, keyParams);
    }

    public NotFoundException(NotFoundExceptionType type, Object[] valueParams, Object... keyParams) {
        super(type, valueParams, keyParams);
    }

    public NotFoundException(NotFoundExceptionType type, Object... valueParams) {
        super(type, valueParams);
    }

}
