package org.example.service;

import org.example.enums.AppCommand;

import java.io.File;
import java.io.IOException;

public class FileServiceImpl implements FileService {

    private File root;

    @Override
    public void updateCurrentDirectory(File newDirectory) {
        this.root = newDirectory;
    }

    @Override
    public void createNewFile(AppCommand state, String fileName) throws IOException {
        if (FileCreator.addNewFile(this.root, state, fileName)) {
            System.out.println("File created successfully in directory \"" + this.root + "\".");
        } else {
            System.out.println("Failed to create new file in directory \"" + this.root + "\".");
        }
    }

    @Override
    public void deleteFile(String fileName) {
        if (FileRemover.deleteFile(this.root, fileName)) {
            System.out.println("File(s) removed from directory \"" + this.root + "\".");
        } else {
            System.out.println("Deleting file(s) from directory \"" + this.root + "\" failed.");
        }
    }

   @Override
    public void updateFileName(String[] fileInfo) {
       File filePathToUpdate = new File(this.root + File.separator + fileInfo[0].trim());
       if (FileUpdater.renameFile(filePathToUpdate, fileInfo[1].trim())) {
           System.out.println("File name updated.");
       } else {
           System.out.println("Failed to rename file since it doesn't exist in directory.");
       }
    }
}
