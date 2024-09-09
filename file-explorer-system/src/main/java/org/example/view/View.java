package org.example.view;

import org.example.model.DirectoryFile;

import java.io.File;
import java.util.List;

public final class View {

    public static final int COLUMN_SEPARATION_WIDTH = 5;
    public static final String COLUMN_SEPARATOR = "";
    public static final String HEADER_NAME = "Name";
    public static final String HEADER_TYPE = "Type";
    public static final String HEADER_SIZE = "Size (KB)";
    public static final String HEADER_PATH = "Path";
    public static final String DIRS = "Dir(s)";
    public static final String FILES = "File(s)";

    private View() {}

    // Not in use.
    public static void printDirectoryDetails(List<DirectoryFile> list) {
        if (!list.isEmpty()) {
            String path = list.get(0).getFullPath();
            System.out.println("Directory of " + path + "\"\n");
            System.out.println(new DirectoryDetails().listDirectoryDetails(list));
        } else {
            System.out.println("List is empty.");
        }
    }

    public static void printDirectoryDetails(File directory) {
        if (directory != null) {
            System.out.println("Directory of \"" + directory + "\"\n");
            System.out.println(new DirectoryDetails().listDirectoryDetails(directory));
        } else {
            System.out.println("Directory path is not valid.");
        }
    }

    public static void printSearchMatchResults(List<DirectoryFile> list) {
        System.out.println(new SearchResults(list).listSearchResults());
    }

    public static void printHelpMenu() {
        System.out.println(HelpMenu.getHelpMenu());
    }
}
