package org.example.service;

import java.io.File;
import java.io.IOException;

public interface FileService {

    void createNewDirectory(File root, String directoryName);

    void createNewFile(File root, String fileName) throws IOException;

    void deleteFile(File root, String fileName);

    void updateFileName(File root, String filename, String updatedName);
}
