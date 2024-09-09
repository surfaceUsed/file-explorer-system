package org.example.enums;

public enum AppCommand {

    BACK(".back"),
    NEXT(".next"),
    CURRENT(".current"),
    CREATE_FILE(".newFile"),
    CREATE_DIR(".newDir"),
    DELETE(".delete"),
    UPDATE(".update"),
    FIND(".find"),
    CHANGE(".change"),
    LIST(".list"),
    HELP(".help"),
    EXIT(".exit"),
    INVALID_VALUE("invalid value");

    private final String key;

    AppCommand(String key) {
        this.key = key;
    }

    private String getKey() {
        return key;
    }

    public static AppCommand getState(String input) {
        for (AppCommand state : AppCommand.values()) {
            if (state.getKey().equals(input.trim())) {
                return state;
            }
        }
        return INVALID_VALUE;
    }
}
