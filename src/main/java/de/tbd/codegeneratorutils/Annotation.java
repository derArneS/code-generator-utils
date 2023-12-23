package de.tbd.codegeneratorutils;

public class Annotation extends AbstractCodable {

    private final java.lang.Class<?> annotationClass;

    private final String[] parameters;

    public Annotation(java.lang.Class<?> annotationClass, String... parameters) {
        this.annotationClass = annotationClass;
        this.parameters = parameters;
    }

    @Override
    public String getCodeAsString() {
        append(at, annotationClass.getName());

        if (parameters.length > 0) {
            append(open, parameters[0]);

            for (int i = 1; i < parameters.length; i++) {
                append(comma, space, parameters[i]);
            }

            append(close);
        }

        return write();
    }
}
