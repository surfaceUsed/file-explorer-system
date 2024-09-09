package org.example.command;

import org.example.service.FileService;

class Delete implements Command {

    private final FileService fileSystem;
    private final String fileName;

    Delete(FileService fileSystem, String fileName) {
        this.fileSystem = fileSystem;
        this.fileName = fileName;
    }

    @Override
    public void execute() {
        this.fileSystem.deleteFile(this.fileName);
    }
}
