package de.tbd.codegeneratorutils;

import java.lang.reflect.Parameter;
import java.util.*;

public class MethodDesc implements StringConstants {

    public static Builder methodBuilder(String name) {
        return new Builder(name);
    }

    public static MethodDesc mapMethod(CodeWriter codeWriter, java.lang.reflect.Method m, String code) {
        Builder methodBuilder = MethodDesc.methodBuilder(m.getName())
                .addAnnotation(AnnotationDesc.annotationBuilder(Override.class)
                        .build()
                )
                .addModifier(Utils.getModifier(m.getModifiers()))
                .returns(m.getReturnType());

        Parameter[] parameters = m.getParameters();
        for (int i = 0; i < parameters.length; i++)
            methodBuilder.addParameter("arg" + i, parameters[i].getType());

        return methodBuilder.build();
    }

    private final List<AnnotationDesc> annotations = new ArrayList<>();

    private final Set<Modifier> modifiers = new HashSet<>();

    private java.lang.Class<?> returnType;

    private final String name;

    private final Map<String, Class<?>> parameters = new HashMap<>();

    private final List<String> code = new ArrayList<>();

    private MethodDesc(String name) {
        this.name = name;
    }

    public void print(CodeWriter codeWriter) {
        for (AnnotationDesc annotation : annotations) {
            codeWriter.append(annotation);
        }

        codeWriter.appendIndentation();
        for (Modifier modifier : modifiers)
            codeWriter.append(modifier.asString, space);

        if (returnType != null) {
            codeWriter.append(returnType.getName());
        } else {
            codeWriter.append("void");
        }

        codeWriter.append(space, name, open);

        boolean isNotFirst = false;
        for (Map.Entry<String, Class<?>> parameter : parameters.entrySet()) {
            if (isNotFirst) codeWriter.append(comma, space);
            codeWriter.append(parameter.getValue().getName(), space, parameter.getKey());
            isNotFirst = true;
        }

        codeWriter.append(close, space, curlyOpen, newLine);

        codeWriter.indent();
        for (String loc : code) {
            codeWriter.appendIndentation();
            codeWriter.append(loc, newLine);
        }
        codeWriter.unindent();

        codeWriter.appendIndentation();
        codeWriter.append(curlyClose, newLine);
    }

    public static class Builder {

        private final MethodDesc method;

        private Builder(String name) {
            method = new MethodDesc(name);
        }

        public Builder addAnnotation(AnnotationDesc annotation) {
            method.annotations.add(annotation);
            return this;
        }

        public Builder addModifier(Modifier modifier) {
            method.modifiers.add(modifier);
            return this;
        }

        public Builder returns(Class<?> returnValue) {
            method.returnType = returnValue;
            return this;
        }

        public Builder addParameter(String name, Class<?> type) {
            method.parameters.put(name, type);
            return this;
        }

        public Builder addLineOfCode(String loc) {
            method.code.add(loc);
            return this;
        }

        public MethodDesc build() {
            return method;
        }

    }
}
