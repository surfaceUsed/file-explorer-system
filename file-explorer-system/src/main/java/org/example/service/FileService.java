package org.example.service;

import org.example.enums.AppCommand;

import java.io.File;
import java.io.IOException;

public interface FileService {

    void updateCurrentDirectory(File directory);

    void createNewFile(AppCommand state, String fileName) throws IOException;

    void deleteFile(String fileName);

    void updateFileName(String[] fileInfo);
}
