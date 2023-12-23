package de.tbd.codegeneratorutils;

import java.lang.reflect.Modifier;

public class Utils {

    public static String getModifierAsString(int modifier) {
        if (Modifier.isPublic(modifier)) {
            return "public";
        } else if (Modifier.isProtected(modifier)) {
            return "protected";
        } else if (Modifier.isPrivate(modifier)) {
            return "private";
        }

        throw new UnknownModifierException(String.valueOf(modifier));
    }
}
