package de.tbd.codegeneratorutils;

public enum Modifier {

    PRIVATE("private"),
    PROTECTED("protected"),
    PUBLIC("public");

    public final String asString;

    Modifier(String asString) {
        this.asString = asString;
    }

}
