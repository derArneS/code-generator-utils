package de.tbd.codegeneratorutils.method;

import de.tbd.codegeneratorutils.AbstractCodable;
import de.tbd.codegeneratorutils.Builder;
import de.tbd.codegeneratorutils.Modifier;
import de.tbd.codegeneratorutils.Pair;
import de.tbd.codegeneratorutils.annotation.Annotation;

import java.util.ArrayList;
import java.util.List;

public class Method extends AbstractCodable implements Builder<Method>, MethodAnnotation, MethodAnnotation.MethodWithAnnotation, MethodModifier, MethodReturnType, MethodName, MethodParameter, MethodParameter.MethodWithParameter, MethodCode {

    private final List<Annotation> annotations = new ArrayList<>();

    private Modifier modifier;

    private java.lang.Class<?> returnType;

    private String name;

    private final List<Pair<Class<?>, String>> parameters = new ArrayList<>();

    private String code;

    private Method() {

    }

    public static MethodAnnotation method() {
        return new Method();
    }

    @Override
    public String getCodeAsString(int tabLevel) {
        for (Annotation annotation : annotations) {
            append(tabLevel, annotation.getCodeAsString(), newLine);
        }

        append(tabLevel, modifier.asString, space, returnType.getName(), space);
        append(name, open);

        if (parameters.size() > 0) {
            append(parameters.get(0).getKey().getName(), space, parameters.get(0).getValue());

            for (int i = 1; i < parameters.size(); i++) {
                append(comma, space, parameters.get(i).getKey().getName(), space, parameters.get(i).getValue());
            }

        }

        append(close, space, curlyOpen, newLine);

        append(tabLevel + 1, code, semicolon, newLine);

        append(tabLevel, curlyClose);

        return write();
    }

    @Override
    public MethodAnnotation.MethodWithAnnotation withAnnotations() {
        return this;
    }

    @Override
    public MethodModifier withoutAnnotations() {
        return this;
    }

    @Override
    public MethodAnnotation.MethodWithAnnotation addAnnotation(Annotation annotation) {
        annotations.add(annotation);
        return this;
    }

    @Override
    public MethodModifier addLastAnnotation(Annotation annotation) {
        annotations.add(annotation);
        return this;
    }

    @Override
    public MethodReturnType withPrivateModifier() {
        modifier = Modifier.PRIVATE;
        return this;
    }

    @Override
    public MethodReturnType withProtectedModifier() {
        modifier = Modifier.PROTECTED;
        return this;
    }

    @Override
    public MethodReturnType withPublicModifier() {
        modifier = Modifier.PUBLIC;
        return this;
    }

    @Override
    public MethodName withReturnType(Class<?> returnType) {
        this.returnType = returnType;
        return this;
    }

    @Override
    public MethodParameter withName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public MethodParameter.MethodWithParameter withParameters() {
        return this;
    }

    @Override
    public MethodCode withoutParameters() {
        return this;
    }

    @Override
    public MethodParameter.MethodWithParameter addParameter(Pair<Class<?>, String> parameter) {
        parameters.add(parameter);
        return this;
    }

    @Override
    public MethodCode addLastParameter(Pair<Class<?>, String> parameter) {
        parameters.add(parameter);
        return this;
    }

    @Override
    public Builder<Method> withCode(String code) {
        this.code = code;
        return this;
    }

    @Override
    public Method build() {
        return this;
    }

}
