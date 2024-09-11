package org.example.command;

import org.example.service.FileService;

import java.io.File;

class Update implements Command {

    private final File root;
    private final FileService fileSystem;
    private final String[] fileInfo;

    Update(File root, FileService fileSystem, String[] fileInfo) {
        this.root = root;
        this.fileSystem = fileSystem;
        this.fileInfo = fileInfo;
    }

    @Override
    public void execute() {
        this.fileSystem.updateFileName(this.root, this.fileInfo);
    }
}
