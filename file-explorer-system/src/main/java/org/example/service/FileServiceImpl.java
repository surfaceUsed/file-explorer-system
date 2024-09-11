package org.example.service;

import org.example.enums.AppCommand;

import java.io.File;
import java.io.IOException;

public class FileServiceImpl implements FileService {

    @Override
    public void createNewFile(File root, AppCommand state, String fileName) throws IOException {
        if (FileCreator.addNewFile(root, state, fileName)) {
            System.out.println("File created successfully in directory \"" + root + "\".");
        } else {
            System.out.println("Failed to create new file in directory \"" + root + "\".");
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
    public void updateFileName(File root, String[] fileInfo) {
        File filePathToUpdate = new File(root + File.separator + fileInfo[0].trim());
        if (FileUpdater.renameFile(filePathToUpdate, fileInfo[1].trim())) {
            System.out.println("File name updated.");
        } else {
            System.out.println("Failed to rename file since it doesn't exist in directory.");
        }
    }
}
