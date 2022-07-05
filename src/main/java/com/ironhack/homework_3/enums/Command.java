package com.ironhack.homework_3.enums;

public enum Command {
    NEW("NEW"),
    SHOW("SHOW"),
    LOOKUP("LOOKUP"),
    CONVERT("CONVERT"),
    CLOSE_LOST("CLOSE-LOST"),
    CLOSE_WON("CLOSE-WON"),
    OPEN("OPEN"),
    REPORT ("REPORT"),
    MEAN("MEAN"),
    MEDIAN ("MEDIAN"),
    MAX ("MAX"),
    MIN ("MIN"),
    POPULATE ("POPULATE"),
    EXIT("EXIT"),
    HELP("HELP");


    private String expectedInput;

    Command(String expectedInput) {
        this.expectedInput = expectedInput;
    }

    public String getExpectedInput() {
        return expectedInput;
    }
}
