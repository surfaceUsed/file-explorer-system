package org.example.controller;

import org.example.command.Command;
import org.example.command.RequestHandler;
import org.example.enums.AppCommand;
import org.example.model.DirectoryFile;
import org.example.service.DirectoryNavigator;
import org.example.service.FileService;
import org.example.service.FileServiceImpl;
import org.example.util.IOUtil;
import org.example.view.View;

import java.io.File;
import java.util.List;

public class DirectoryController {

    private final FileService fileSystem;
    private AppCommand currentState;
    private boolean exitFileSystem;
    private File root;
    private File currentDirectory;

    public DirectoryController() {
        this.fileSystem = new FileServiceImpl();
        this.exitFileSystem = false;
    }

    public void start() {

        welcomeMessage();

        while (!this.exitFileSystem) {

                updateState(IOUtil.writeInput());

                switch (this.currentState) {

                    case CURRENT:
                        break;

                    case HELP:
                        printHelpMenu();
                        break;

                    case CREATE_FILE, CREATE_DIR, DELETE, UPDATE:
                        modifyFileInFileSystem();
                        break;

                    case BACK:
                        goToPreviousDirectory();
                        break;

                    case NEXT:
                        goToNextDirectory();
                        break;

                    case FIND:
                        searchForFiles();
                        break;

                    case CHANGE:
                        changeRootDirectory();
                        break;

                    case LIST:
                        printDirectoryDetails();
                        break;

                    case EXIT:
                        exitApplication();
                        break;

                    default:
                        System.out.println("***** Invalid input command *****");
                        break;
                }

        }
    }

    private void welcomeMessage() {
        System.out.println("Please share the directory you want to work with: ");
        String startDirectory = IOUtil.writeInput();
        this.root = new File(startDirectory);
        this.currentDirectory = new File(startDirectory);
        if (!this.root.isDirectory()) {
            System.out.println("***** Not a valid directory! *****");
            this.exitFileSystem = true;
        } else {
            this.fileSystem.updateCurrentDirectory(this.root);
            View.printDirectoryDetails(this.root);
            System.out.println("Enter the action you want to take:");
        }
    }

    private void printHelpMenu() {
        View.printHelpMenu();
    }

    private void modifyFileInFileSystem() {
        String info = (this.currentState == AppCommand.UPDATE) ?
                "Write the name for file to update, and the new updated name separated by a \",\"." :
                "Write name of file/directory: ";
        System.out.println(info);
        String[] userInput = parseInput();
        Command request = RequestHandler.getRequest(this.currentState, this.fileSystem, userInput);
        if (request != null) {
            request.execute();
        } else {
            System.out.println("***** No modification made *****");
        }
    }

    private String[] parseInput() {
        return IOUtil.writeInput().split(",", 2);
    }

    private void goToPreviousDirectory() {
        File previousDirectory = DirectoryNavigator.getPreviousDirectory(this.currentDirectory);
        if (previousDirectory != null && !this.currentDirectory.equals(this.root)) {
            this.currentDirectory = previousDirectory;
            this.fileSystem.updateCurrentDirectory(this.currentDirectory);
            System.out.println("Directory of \"" + previousDirectory.getPath() + "\".");
        } else {
            System.out.println("***** Already in final root directory *****");
        }
    }

    private void goToNextDirectory() {
        System.out.println("Enter name of directory you want to go to:");
        String directory = IOUtil.writeInput();
        File nextDirectory = DirectoryNavigator.getNextDirectory(this.currentDirectory, directory);
        if (nextDirectory != null) {
            this.currentDirectory = nextDirectory;
            this.fileSystem.updateCurrentDirectory(this.currentDirectory);
            System.out.println("Directory of \"" + nextDirectory.getPath() + "\".");
        } else {
            System.out.println("***** Invalid directory *****");
        }
    }

    private void searchForFiles() {
        System.out.println("Search for file/directory:");
        List<DirectoryFile> matches = DirectoryNavigator.findAllMatchingFiles(this.root, IOUtil.writeInput());
        View.printSearchMatchResults(matches);
    }

    private void changeRootDirectory() {
        System.out.println("Enter new directory path:");
        File directory = new File(IOUtil.writeInput());
        if (directory.isDirectory()) {
            this.root = directory;
            this.currentDirectory = directory;
            this.fileSystem.updateCurrentDirectory(this.root);
            System.out.println("Directory changed successfully.");
        } else {
            System.out.println("***** Invalid directory path *****");
        }
    }

    private void printDirectoryDetails() {
        View.printDirectoryDetails(this.currentDirectory);
    }

    private void exitApplication() {
        this.exitFileSystem = true;
        IOUtil.close();
    }

    private void updateState(String userInput) {
        AppCommand updatedState = AppCommand.getState(userInput);
        if (updatedState != AppCommand.INVALID_VALUE) {
            this.currentState = updatedState;
        } else {
            System.out.println("***** Not a valid command *****");
            this.currentState = AppCommand.CURRENT;
        }
    }
}
