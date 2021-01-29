package com.example.parking.utils;

import com.example.parking.exception.NotFoundException;
import org.springframework.util.CollectionUtils;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URLConnection;
import java.util.List;

public class FileUtils {
    private FileUtils() {

    }

    /**
     * Read a file and return a byte array
     *
     * @param filePath the file path to read
     * @return a byte array
     *                           if the file is not found
     */
    public static byte[] readFile(String filePath) throws Exception {
        byte[] bytes = null;
        File file = new File(filePath);

        if (!file.exists()) {
            throw new NotFoundException(NotFoundException.NotFoundExceptionType.FILE_NOT_FOUND, filePath);
        }
        try {
            bytes = FileCopyUtils.copyToByteArray(file);
        } catch (IOException e) {
            throw new Exception(e);
        }

        return bytes;
    }

    /**
     * Retrieve the content type for a specific filename
     *
     * @param filePath
     * @return the mime type
     */
    public static String getContentType(String filePath) {
        String mimeType = URLConnection.guessContentTypeFromName(filePath);
        if (mimeType == null) {
            mimeType = FileType.getByFilename(filePath).getContentType();
        }
        return mimeType;
    }

    /**
     * Generic method that transforms a list into an array
     *
     * @param list  the list to transform
     * @param clazz class of the generic type
     * @return an array of the generic type if the list is not empty, otherwise an
     * empty array
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] toArray(List<T> list, Class<T> clazz) {
        return !CollectionUtils.isEmpty(list) ? list.toArray((T[]) (Array.newInstance(clazz, list.size()))) : (T[]) Array.newInstance(clazz, 0);
    }
}
