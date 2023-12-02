package de.tbd.codegeneratorutils;

public abstract class AbstractCodable implements Codable, StringConstants {

    protected final StringBuilder sb = new StringBuilder();

    protected void append(String... strings) {
        append(0, strings);
    }

    protected void append(int tablevel, String... strings) {
        sb.append("    ".repeat(Math.max(0, tablevel)));
        for (String s : strings) sb.append(s);
    }
}
