package de.tbd.codegeneratorutils.method;

public interface MethodModifier {

    MethodReturnType withPrivateModifier();

    MethodReturnType withProtectedModifier();

    MethodReturnType withPublicModifier();
}
