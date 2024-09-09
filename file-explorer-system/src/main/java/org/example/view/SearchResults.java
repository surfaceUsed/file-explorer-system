package org.example.view;

import org.example.model.DirectoryFile;

import java.util.List;

final class SearchResults {

    private int fileNameWidth = View.HEADER_NAME.length();
    private int fileTypeWidth = View.HEADER_TYPE.length();
    private int fileSizeWidth = View.HEADER_SIZE.length();
    private int filePathWidth = View.HEADER_PATH.length();

    private final List<DirectoryFile> listOfFiles;

    public SearchResults(List<DirectoryFile> list) {
        this.listOfFiles = list;
        initWidths(this.listOfFiles);
    }

    String listSearchResults() {
        return createHeader() + createBody() + createFooter();
    }

    private void initWidths(List<DirectoryFile> list) {
        for (DirectoryFile file : list) {
            String fileName = file.getFileName();
            String fileType = file.getFileType();
            String fileSize = String.valueOf(file.getSize());
            String filePath = file.getFullPath();
            updateWidth(fileName.length(), fileType.length(), fileSize.length(), filePath.length());
        }
    }

    private void updateWidth(int fileNameLength, int fileTypeLength, int fileSizeLength, int filePathSize) {
        if (fileNameLength > this.fileNameWidth) {
            this.fileNameWidth = fileNameLength;
        }
        if (fileTypeLength > this.fileTypeWidth) {
            this.fileTypeWidth = fileTypeLength;
        }
        if (fileSizeLength > this.fileSizeWidth) {
            this.fileSizeWidth = fileSizeLength;
        }
        if (filePathSize > this.filePathWidth) {
            this.filePathWidth = filePathSize;
        }
    }

    private String createHeader() {
        return String.format("%-" + (this.fileNameWidth + View.COLUMN_SEPARATION_WIDTH) + "s " +
                        "%-" + (this.fileTypeWidth + View.COLUMN_SEPARATION_WIDTH) + "s " +
                        "%-" + (this.fileSizeWidth + View.COLUMN_SEPARATION_WIDTH) + "s " +
                        "%-" + this.filePathWidth + "s%n",
                View.HEADER_NAME, View.HEADER_TYPE, View.HEADER_SIZE, View.HEADER_PATH);
    }

    private String createBody() {
        StringBuilder sb = new StringBuilder();
        for (DirectoryFile file : this.listOfFiles) {
            sb.append(String.format("%-" + this.fileNameWidth + "s " +
                            "%" + (this.fileTypeWidth + View.COLUMN_SEPARATION_WIDTH) + "s " +
                            "%" + (this.fileSizeWidth + View.COLUMN_SEPARATION_WIDTH) + "d " +
                            "%" + + View.COLUMN_SEPARATION_WIDTH + "s" +
                            "%-" + this.filePathWidth + "s%n",
                    file.getFileName(), file.getFileType(), file.getSize(), View.COLUMN_SEPARATOR, file.getFullPath()));
        }
        return sb.toString();
    }

    private String createFooter() {
        return "=".repeat(totalLength());
    }

    private int totalLength() {
        return (this.fileNameWidth + View.COLUMN_SEPARATION_WIDTH) +
                (this.fileTypeWidth + View.COLUMN_SEPARATION_WIDTH) +
                (this.fileSizeWidth + View.COLUMN_SEPARATION_WIDTH) +
                (this.filePathWidth + View.COLUMN_SEPARATION_WIDTH);
    }
}
