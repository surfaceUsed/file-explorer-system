package org.example.service;

import org.example.enums.AppCommand;

import java.io.File;
import java.io.IOException;

public interface FileService {

    void createNewFile(File root, AppCommand state, String fileName) throws IOException;

    void deleteFile(File root, String fileName);

    void updateFileName(File root, String[] fileInfo);
}
