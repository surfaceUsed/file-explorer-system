package org.example.command;

import org.example.enums.AppCommand;
import org.example.service.FileService;

import java.io.IOException;

class Add implements Command {

    private final AppCommand state;
    private final FileService fileDirectory;
    private final String fileName;

    Add(AppCommand state, FileService fileDirectory, String fileName) {
        this.state = state;
        this.fileDirectory = fileDirectory;
        this.fileName = fileName;
    }

    @Override
    public void execute() {
        try {
            this.fileDirectory.createNewFile(this.state, this.fileName);
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }
}
