package de.tbd.codegeneratorutils;

public class CodeWriter implements StringConstants {

    private final StringBuilder sb = new StringBuilder();

    private int indentation = 0;

    public void indent() {
        indentation = indentation + 1;
    }

    public void unindent() {
        indentation = indentation - 1;
    }

    public void append(String... strings) {
        for (String s : strings) sb.append(s);
    }

    public void appendIndentation() {
        sb.append("    ".repeat(Math.max(0, indentation)));
    }

    public void append(AnnotationDesc annotation) {
        annotation.print(this);
    }

    public void append(MethodDesc method) {
        method.print(this);
    }

    public String print() {
        return sb.toString();
    }
}
