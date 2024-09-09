package org.example.service;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public final class FileRemover {

    private FileRemover() {}

    public static boolean deleteFile(File root, String fileToDelete) {
        File rootFile = new File(root + File.separator + fileToDelete);

        if (rootFile.exists() && rootFile.isFile()) {
            return rootFile.delete();

        } else {

            if (rootFile.exists()) {

                LinkedList<File> directoriesToTraverse = new LinkedList<>();
                LinkedList<File> directoriesToDelete = new LinkedList<>();

                directoriesToTraverse.add(rootFile);
                directoriesToDelete.addFirst(rootFile);

                while (!directoriesToTraverse.isEmpty()) {

                    rootFile = directoriesToTraverse.removeFirst();

                    File[] files = rootFile.listFiles();

                    if (files != null) {
                        for (File file : files) {
                            if (file.isDirectory()) {
                                directoriesToDelete.addFirst(file);
                                directoriesToTraverse.addLast(file);
                            } else {
                                if (!file.delete()) {
                                    System.out.println("Failed to delete file at \"" + file.getPath() + "\".");
                                    return false;
                                }
                            }
                        }
                    }
                }
                return deleteDirectories(directoriesToDelete);
            }
        }
        System.out.println("Failed to delete file at path \"" + rootFile.getPath() + "\".");
        return false;
    }

    private static boolean deleteDirectories(List<File> listOfDirectories) {
        for (File directories : listOfDirectories) {
            if (!directories.delete()) {
                System.out.println("Failed to delete directory at \"" + directories.getPath() + "\".");
                return false;
            }
        }
        return true;
    }
}
