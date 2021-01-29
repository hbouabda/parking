package com.example.parking.service;

import java.io.IOException;

public interface FileStorageService {
    /**
     * Gets the full path of a file path contained in statics folder
     *
     * @param path the file path to contact
     * @return file path concats with root path
     */
    String getStaticsPath(String path);

    /**
     * Upload d'un fichier sur le NAS
     *
     * @param content Le contenu du fichier
     * @return Le nom du fichier sur le serveur (UUID)
     */
    String storeFile(byte[] content) throws IOException;

    /**
     * Charge un fichier sous forme de tableau de bytes depuis le NAS
     * @param uuid l'identifiant unique du fichier
     * @return le fichier sous forme binaires
     */
    byte[] loadFile(String uuid);

    /**
     * Supprimer un fichier sur le NAS
     * @param fileUuid
     */
    void deleteFile(String fileUuid);

    /**
     * Charge la photo ou l'annotation sous forme de tableau de bytes depuis le NAS
     *
     * @param annotated
     * @param uuidPhoto      l'identifiant unique de la photo
     * @param uuidAnnotation l'identifiant unique de l'annotation
     * @return le fichier sous forme binaire
     */
    byte[] loadAndAnnotatePhoto(boolean annotated, String uuidPhoto, String uuidAnnotation);

    /**
     * Check si l'extension du fichier est valide.
     * Si l'extension n'est pas valide une exception fonctionnelle est rejetée.
     *
     * @param fileName le nom du fichier complet.
     */
    void checkExtensionAllowed(String fileName);
}
