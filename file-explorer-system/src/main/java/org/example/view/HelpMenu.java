package org.example.view;

final class HelpMenu {

    private HelpMenu() {}

    static String getHelpMenu() {
        return """
                .back     --- Traverse to the parent directory of the current directory.
                .next     --- Traverse to a new directory located in the current directory.
                .newFile  --- Create a new file in the current directory.
                .newDir   --- Create a new directory in the current directory.
                .delete   --- Delete a file/directory in the current directory.
                .update   --- Change the name of a file/directory in the current directory.
                .find     --- Search for a file in the current directory
                .change   --- Change root directory.
                .list     --- Lists all the files in the current directory.
                .help     --- Print list of command inputs.
                .exit     --- Terminates application.
                """;
    }
}
