package org.example.service;

import java.io.File;
import java.io.IOException;

public class FileServiceImpl implements FileService {

    @Override
    public void createNewDirectory(File root, String directoryName) {
        if (FileCreator.createNewDirectory(root, directoryName)) {
            System.out.println("Directory created successfully in \"" + root + "\".");
        } else {
            System.out.println("Failed to create new directory in \"" + root + "\".");
        }
    }

    @Override
    public void createNewFile(File root, String fileName) throws IOException {
        if (FileCreator.createNewFile(root, fileName)) {
            System.out.println("File created successfully in \"" + root + "\".");
        } else {
            System.out.println("Failed to create new file in \"" + root + "\".");
        }
    }

    @Override
    public void deleteFile(File root, String fileName) {
        if (FileRemover.deleteFile(root, fileName)) {
            System.out.println("File(s) removed from directory \"" + root + "\".");
        } else {
            System.out.println("Deleting file(s) from directory \"" + root + "\" failed.");
        }
    }

    @Override
    public void updateFileName(File root, String fileName, String updatedName) {
        File filePathToUpdate = new File(root + File.separator + fileName);
        if (FileUpdater.renameFile(filePathToUpdate, updatedName)) {
            System.out.println("File name updated.");
        } else {
            System.out.println("Failed to rename file since it doesn't exist in directory.");
        }
    }
}
