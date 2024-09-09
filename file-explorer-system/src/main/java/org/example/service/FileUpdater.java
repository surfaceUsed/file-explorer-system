package org.example.service;

import java.io.File;

public final class FileUpdater {

    private FileUpdater() {}

    public static boolean renameFile(File root, String newName) {
        boolean fileNameUpdated = false;
        if (root.exists()) {
            File updatedFile = new File(root.getParent() + File.separator + newName);
            fileNameUpdated = root.renameTo(updatedFile);
        }
        return fileNameUpdated;
    }
}
