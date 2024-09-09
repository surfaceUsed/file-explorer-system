package org.example.command;

import org.example.service.FileService;

class Update implements Command {

    private final FileService fileSystem;
    private final String[] fileInfo;

    Update(FileService fileSystem, String[] fileInfo) {
        this.fileSystem = fileSystem;
        this.fileInfo = fileInfo;
    }

    @Override
    public void execute() {
        this.fileSystem.updateFileName(this.fileInfo);
    }
}
