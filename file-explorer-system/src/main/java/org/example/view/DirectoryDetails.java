package org.example.view;

import org.example.model.DirectoryFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

final class DirectoryDetails {

    private int fileNameWidth = View.HEADER_NAME.length();
    private int fileTypeWidth = View.HEADER_TYPE.length();
    private int fileSizeWidth = View.HEADER_SIZE.length();

    DirectoryDetails() {}

    String listDirectoryDetails(List<DirectoryFile> list) {
        return createHeader() + createBody(list) + createFooter(list);
    }

    String listDirectoryDetails(File directory) {
        List<DirectoryFile> listOfFiles = getDirectoryFilesAsList(directory);
        return createHeader() + createBody(listOfFiles) + createFooter(listOfFiles);
    }

    private List<DirectoryFile> getDirectoryFilesAsList(File directoryPath) {
        List<DirectoryFile> directory = new ArrayList<>();
        File[] files = directoryPath.listFiles();
        if (files.length > 0) {
            for (File file : files) {
                DirectoryFile newFile = new DirectoryFile(file);
                updateTextFormatterVariables(newFile);
                directory.add(newFile);
            }
        } else {
            System.out.println("Empty directory.");
        }
        return directory;
    }

    private void updateTextFormatterVariables(DirectoryFile file) {
        String fileName = file.getFileName();
        String fileType = file.getFileType();
        String fileSize = String.valueOf(file.getSize());
        updateWidths(fileName.length(), fileType.length(), String.valueOf(fileSize).length());
    }

    private void updateWidths(int fileNameLength, int fileTypeLength, int fileSizeLength) {
        if (fileNameLength > this.fileNameWidth) {
            this.fileNameWidth = fileNameLength;
        }
        if (fileTypeLength > this.fileTypeWidth) {
            this.fileTypeWidth = fileTypeLength;
        }
        if (fileSizeLength > this.fileSizeWidth) {
            this.fileSizeWidth = fileSizeLength;
        }
    }

    private String createHeader() {
        return String.format("%-" + (this.fileNameWidth + View.COLUMN_SEPARATION_WIDTH) + "s " +
                        "%-" + (this.fileTypeWidth + View.COLUMN_SEPARATION_WIDTH) + "s " +
                        "%-" + (this.fileSizeWidth + View.COLUMN_SEPARATION_WIDTH) + "s%n",
                View.HEADER_NAME, View.HEADER_TYPE, View.HEADER_SIZE);
    }

    private String createBody(List<DirectoryFile> list) {
        StringBuilder sb = new StringBuilder();
        for (DirectoryFile file : list) {
            sb.append(String.format("%-" + this.fileNameWidth + "s " +
                            "%" + (this.fileTypeWidth + View.COLUMN_SEPARATION_WIDTH) + "s " +
                            "%" + (this.fileSizeWidth + View.COLUMN_SEPARATION_WIDTH) + "d %n",
                    file.getFileName(), file.getFileType(), file.getSize()));
        }
        return sb.toString();
    }

    private String createFooter(List<DirectoryFile> list) {
        String start = "=".repeat(totalLength());
        int numberOfDirectories = countDirectories(list);
        int numberOfFiles = list.size() - numberOfDirectories;
        return String.format("%s%n%-10s %5d%n%-10s %5d",
                start, View.DIRS, numberOfDirectories, View.FILES, numberOfFiles);
    }

    private int totalLength() {
        return (this.fileNameWidth + View.COLUMN_SEPARATION_WIDTH) +
                (this.fileTypeWidth + View.COLUMN_SEPARATION_WIDTH) +
                (this.fileSizeWidth + View.COLUMN_SEPARATION_WIDTH);
    }

    private static int countDirectories(List<DirectoryFile> list) {
        int count = 0;
        for (DirectoryFile file : list) {
            if (file.isDirectory()) {
                count++;
            }
        }
        return count;
    }
}
