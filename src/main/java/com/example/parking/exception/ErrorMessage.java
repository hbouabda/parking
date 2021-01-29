package com.example.parking.exception;

public enum ErrorMessage {

    // user guide
    USER_GUIDE_NOT_FOUND("error.server.userguide.deletion.impossible", "userguide.deletion.impossible"),
    DEFAULT_INTERNAL_SERVER_ERROR("error.server.userguide.deletion.impossible", "userguide.deletion.impossible"),
    USER_GUIDE_DELETION_IMPOSSIBLE("error.server.userguide.deletion.impossible", "userguide.deletion.impossible"),
    FILE_UPLOAD_ERROR("error.server.userguide.deletion.impossible", "userguide.deletion.impossible"),
    FILE_NOT_FOUND("error.server.userguide.deletion.impossible", "userguide.deletion.impossible"),
    FILE_DELETE_ERROR("error.server.userguide.deletion.impossible", "userguide.deletion.impossible"),
    FILE_EXTENSION_NOT_ALLOWED("error.server.userguide.deletion.impossible", "userguide.deletion.impossible"),
    FILE_DOWNLOAD_ERROR("error.server.userguide.deletion.impossible", "userguide.deletion.impossible"),
    ENUM_FOR_VALUE_UNKNOWN("error.server.userguide.deletion.impossible", "userguide.deletion.impossible"),
    INVALID_CSV_FORMAT("error.server.userguide.deletion.impossible", "userguide.deletion.impossible");

    private final String baseKey;
    private final String messageCauseKey;

    ErrorMessage(String baseKey, String msgCauseKey) {
        this.baseKey = baseKey;
        this.messageCauseKey = msgCauseKey;
    }

    public String getTitleKey() {
        return baseKey + ".title";
    }

    public String getMessageKey() {
        return baseKey + ".msg";
    }

    public String getMessageCauseKey() {
        return messageCauseKey;
    }

}
