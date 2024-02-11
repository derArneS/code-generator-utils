package de.tbd.codegeneratorutils;

import java.util.*;

public class AnnotationDesc implements StringConstants {

    public static Builder annotationBuilder(Class<?> annotationClass) {
        return new Builder(annotationClass);
    }

    private final Map<String, List<String>> parameters = new HashMap<>();

    private final java.lang.Class<?> annotationClass;

    private AnnotationDesc(Class<?> annotationClass) {
        this.annotationClass = annotationClass;
    }

    private void addAnnotationParameter(String parameterName, String... parameterValues) {
        parameters.put(parameterName, List.of(parameterValues));
    }

    public void print(CodeWriter codeWriter) {
        codeWriter.appendIndentation();
        codeWriter.append(at, annotationClass.getName().replace("$", "."));

        if (parameters.isEmpty()) {
            codeWriter.append(newLine);
            return;
        }
        codeWriter.append(open);
        if (parameters.size() == 1 && parameters.get("value") != null) {
            codeWriter.append(printParameterValues(parameters.get("value")), close, newLine);
            return;
        }

        boolean notFirst = false;
        for (Map.Entry<String, List<String>> entry : parameters.entrySet()) {
            if (notFirst) codeWriter.append(comma, space);
            codeWriter.append(entry.getKey(), space, "=", space, printParameterValues(entry.getValue()));
            notFirst = true;
        }
        codeWriter.append(close, newLine);
    }

    private String printParameterValues(List<String> parameterValues) {
        if (parameterValues.size() == 0) return "";
        if (parameterValues.size() == 1) return parameterValues.get(0);

        StringBuilder valueArray = new StringBuilder("{");
        boolean notFirst = false;
        for (String value : parameterValues) {
            if (notFirst) valueArray.append(", ");
            valueArray.append(value);
            notFirst = true;
        }

        return valueArray + "}";
    }

    public static class Builder {
        private final AnnotationDesc annotationDesc;

        private Builder(Class<?> annotationClass) {
            this.annotationDesc = new AnnotationDesc(annotationClass);
        }

        public Builder addParameter(String parameterName, String... parameterValues) {
            this.annotationDesc.addAnnotationParameter(parameterName, parameterValues);
            return this;
        }

        public AnnotationDesc build() {
            return annotationDesc;
        }
    }


}
