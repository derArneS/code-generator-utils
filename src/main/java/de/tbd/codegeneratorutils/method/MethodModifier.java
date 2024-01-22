package de.tbd.codegeneratorutils.method;

import de.tbd.codegeneratorutils.Modifier;

public interface MethodModifier {

    default MethodReturnType withModifier(Modifier modifier) {
        return switch (modifier) {
            case PUBLIC -> withPublicModifier();
            case PRIVATE -> withPrivateModifier();
            case PROTECTED -> withProtectedModifier();
        };
    }

    MethodReturnType withPrivateModifier();

    MethodReturnType withProtectedModifier();

    MethodReturnType withPublicModifier();
}
