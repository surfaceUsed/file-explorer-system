package org.example.command;

import org.example.enums.AppCommand;
import org.example.service.FileService;

import java.io.File;

public abstract class RequestHandler {

    public static Command getRequest(File root, AppCommand state, FileService service, String[] fileInfo) {

        if (state != AppCommand.INVALID_VALUE) {

            switch (state) {

                case CREATE_DIR, CREATE_FILE:
                    if (fileInfo.length == 1) {
                        return new Add(root, state, service, fileInfo[0].trim());
                    } else {
                        System.out.println("Not a valid file name.");
                    }
                    break;

                case DELETE:
                    if (fileInfo.length == 1) {
                        return new Delete(root, service, fileInfo[0].trim());
                    } else {
                        System.out.println("Not a valid file name.");
                    }
                    break;

                case UPDATE:
                    if (fileInfo.length == 2) {
                        return new Update(root, service, fileInfo);
                    } else {
                        System.out.println("Missing input for updating file name.");
                    }
                    break;

                default:
                    System.out.println("Incorrect request type");
                    break;
            }
        }
        return null;
    }
}
