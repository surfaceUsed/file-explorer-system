package org.example.service;

import java.io.File;
import java.io.IOException;

public final class FileCreator {

    private FileCreator() {}

    public static boolean createNewFile(File root, String fileName) throws IOException {
        File file = new File(root + File.separator + fileName);
        boolean fileCreated = false;
        if (!file.exists()) {
            fileCreated = file.createNewFile();
        }
        return fileCreated;
    }

    public static boolean createNewDirectory(File root, String directoryName) {
        File directory = new File(root + File.separator + directoryName);
        boolean directoryCreated = false;
        if (!directory.exists()) {
            directoryCreated = directory.mkdir();
        }
        return directoryCreated;
    }
}
