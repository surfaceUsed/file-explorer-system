package org.example.model;

import java.io.File;

public class DirectoryFile {

    private static final char FILE_SEPARATOR = '.';
    private static final String UNDEFINED_FILE_TYPE = "<NULL>";
    private static final String DIRECTORY_TYPE = "<DIR>";

    private final File file;
    private final String fileName;
    private final Long size;
    private final boolean isDirectory;
    private final String fileType;
    private final String fullPath;

    public DirectoryFile(File file) {
        this.file = file;
        this.fileName = file.getName();
        this.size = file.length();
        this.isDirectory = file.isDirectory();
        this.fileType = parseFileType(this.fileName, this.isDirectory);
        this.fullPath = file.getParent();
    }

    public File getFile() {
        return file;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public Long getSize() {
        return size;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public String getFullPath() {
        return fullPath;
    }

    private String parseFileType(String fileName, boolean isDirectory) {
        if (!isDirectory) {
            StringBuilder sb = new StringBuilder();
            char character = ' ';
            int start = fileName.length() - 1;

            while (character != FILE_SEPARATOR) {
                sb.append(fileName.charAt(start));
                character = fileName.charAt(--start);
                if (start == 0 && character != FILE_SEPARATOR) {
                    return UNDEFINED_FILE_TYPE;
                }
            }
            return "<" + sb.reverse().toString().toUpperCase() + ">";
        }
        return DIRECTORY_TYPE;
    }

    @Override
    public String toString() {
        return  "Directory File:\n" +
                "{\n" +
                "\t\"fileName\": " + "\"" + this.fileName + "\",\n" +
                "\t\"fileType\": " + "\"" + this.fileType + "\",\n" +
                "\t\"isDirectory\": " + this.isDirectory + ",\n" +
                "\t\"size\": " + this.size + ",\n" +
                "\t\"fullPath\": " + "\"" + this.fullPath + "\n" +
                "}";
    }
}
