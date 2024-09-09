package org.example.service;

import org.example.model.DirectoryFile;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public final class DirectoryNavigator {

    private DirectoryNavigator() {}

    public static File getPreviousDirectory(File root) {
        return root.isDirectory() ? root.getParentFile() : null;
    }

    public static File getNextDirectory(File root, String directory) {
        File newDirectory = new File(root + File.separator + directory);
        return newDirectory.isDirectory() ? newDirectory : null;
    }

    public static List<DirectoryFile> findAllMatchingFiles(File root, String searchKey) {

        List<DirectoryFile> matchingFiles = new ArrayList<>();
        LinkedList<File> directoryList = new LinkedList<>();

        directoryList.add(root);

        while (!directoryList.isEmpty()) {

            File path = directoryList.removeFirst();
            File[] files = path.listFiles();

            if (files != null) {

                for (File file : files) {
                    if (file.getName().contains(searchKey)) {
                        matchingFiles.add(new DirectoryFile(file));
                    }
                    if (file.isDirectory()) {
                        directoryList.addLast(file);
                    }
                }
            }
        }
        return matchingFiles;
    }
}