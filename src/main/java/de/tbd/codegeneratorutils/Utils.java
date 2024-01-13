package de.tbd.codegeneratorutils;

public class Utils {

    public static Modifier getModifier(int modifier) {
        if (java.lang.reflect.Modifier.isPublic(modifier)) {
            return Modifier.PUBLIC;
        } else if (java.lang.reflect.Modifier.isProtected(modifier)) {
            return Modifier.PROTECTED;
        } else if (java.lang.reflect.Modifier.isPrivate(modifier)) {
            return Modifier.PRIVATE;
        }

        throw new UnknownModifierException(String.valueOf(modifier));
    }
}
