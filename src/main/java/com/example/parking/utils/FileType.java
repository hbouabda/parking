package com.example.parking.utils;

public enum FileType {

    DEFAULT("", "application/octet-stream"),
    SVG(".svg", "image/svg+xml");

    private final String extension;

    private final String contentType;

    FileType(String extension, String contentType) {
        this.extension = extension;
        this.contentType = contentType;
    }

    public static FileType getByFilename(String fileName) {

        FileType value = FileType.DEFAULT;
        for (FileType fileType : FileType.values()) {
            if (!fileType.equals(FileType.DEFAULT) && fileName.endsWith(fileType.getExtension())) {
                value = fileType;
                break;
            }
        }
        return value;
    }

    public String getExtension() {
        return extension;
    }

    public String getContentType() {
        return contentType;
    }

}

