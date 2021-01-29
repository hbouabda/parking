package com.example.parking.service.impl;

import com.example.parking.exception.FunctionalException;
import com.example.parking.exception.NotFoundException;
import com.example.parking.properties.AppProperties;
import com.example.parking.properties.ArgosProperties;
import com.example.parking.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


/**
 * Implémentatino du service de gestion des fichiers
 */
@Service
@RequiredArgsConstructor
public class FileStorageServiceImpl implements FileStorageService {

    private final AppProperties appProperties;
    private final ArgosProperties argosProperties;

    private static final String STATICS_PATH = "statics";
    private static final Set<String> EXTENSIONS = new HashSet<>(Arrays.asList(
            "png", "jpeg", "jpg", "gif", "doc", "docx", "xls", "xlsx", "ppt", "pptx", "pdf", "bmp", "csv"
    ));

    /**
     * {@inheritDoc}
     */
    @Override
    public String getStaticsPath(String path) {
        return new StringBuilder(this.appProperties.getFiles().getBasePath())
                .append(File.separator)
                .append(STATICS_PATH)
                .append(File.separator)
                .append(path)
                .toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String storeFile(byte[] content) throws IOException {
        String uuid = UUID.nameUUIDFromBytes(content).toString();
        Path path = Paths.get(argosProperties.getNasBasePath()).resolve(uuid);

        try {
            // Ecriture du fichier sur le serveur seulement s'il n'existe pas déjà
            if (!Files.exists(path)) {
                Files.write(path, content);
            }

            return uuid;
        } catch (IOException e) {
            throw new FunctionalException(FunctionalException.FunctionalExceptionType.FILE_UPLOAD_ERROR);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] loadFile(String uuid) {
        Path filePath = Paths.get(argosProperties.getNasBasePath()).resolve(uuid);

        if (!Files.exists(filePath)) {
            throw new NotFoundException(NotFoundException.NotFoundExceptionType.FILE_NOT_FOUND, filePath);
        }

        try {
            return Files.readAllBytes(filePath);
        } catch (IOException e) {
            throw new FunctionalException(FunctionalException.FunctionalExceptionType.FILE_DOWNLOAD_ERROR, filePath);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteFile(String uuid) {
        Path filePath = Paths.get(argosProperties.getNasBasePath()).resolve(uuid);

        if (!Files.exists(filePath)) {
            throw new NotFoundException(NotFoundException.NotFoundExceptionType.FILE_NOT_FOUND, filePath);
        }

        try {
            Files.delete(filePath);
        } catch (IOException e) {
            throw new FunctionalException(FunctionalException.FunctionalExceptionType.FILE_DELETE_ERROR, filePath);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] loadAndAnnotatePhoto(boolean annotated, String uuidPhoto, String uuidAnnotation) {
        Path photoPath = Paths.get(argosProperties.getNasBasePath()).resolve(uuidPhoto);

        if (!Files.exists(photoPath)) {
            throw new NotFoundException(NotFoundException.NotFoundExceptionType.FILE_NOT_FOUND, photoPath);
        }

        BufferedImage image = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            image = ImageIO.read(photoPath.toFile());


            if (annotated && uuidAnnotation != null) {
                // Fusion des annotations avec l'image
                Path annotationPath = Paths.get(argosProperties.getNasBasePath()).resolve(uuidAnnotation);

                if (Files.exists(annotationPath)) {
                    BufferedImage layer = ImageIO.read(annotationPath.toFile());
                    Graphics graph = image.getGraphics();
                    graph.drawImage(layer, 0, 0, image.getWidth(), image.getHeight(), null);
                    graph.dispose();
                }
            }


            ImageIO.write(image, "jpg", outputStream);
        } catch (IOException e) {

        }
        return outputStream.toByteArray();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void checkExtensionAllowed(String fileName) {
        String extension = FilenameUtils.getExtension(fileName);
        if (!EXTENSIONS.contains(extension)) {
            throw new FunctionalException(FunctionalException.FunctionalExceptionType.FILE_EXTENSION_NOT_ALLOWED, extension);
        }
    }


}
