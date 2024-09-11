package org.example.command;

import org.example.service.FileService;

import java.io.File;

class Delete implements Command {

    private final File root;
    private final FileService fileSystem;
    private final String fileName;

    Delete(File root, FileService fileSystem, String fileName) {
        this.root = root;
        this.fileSystem = fileSystem;
        this.fileName = fileName;
    }

    @Override
    public void execute() {
        this.fileSystem.deleteFile(this.root, this.fileName);
    }
}
