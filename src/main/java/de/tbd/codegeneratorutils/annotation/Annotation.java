package de.tbd.codegeneratorutils.annotation;

import de.tbd.codegeneratorutils.AbstractCodable;
import de.tbd.codegeneratorutils.Builder;

import java.util.ArrayList;
import java.util.List;

public class Annotation extends AbstractCodable implements Builder<Annotation>, AnnotationClass, AnnotationParameter, AnnotationParameter.AnnotationWithParameter {

    private java.lang.Class<?> annotationClass;

    private final List<String> parameters = new ArrayList<>();

    private Annotation() {

    }

    public static AnnotationClass annotation() {
        return new Annotation();
    }

    @Override
    public String getCodeAsString(int tabLevel) {
        append(tabLevel, at, annotationClass.getName());

        if (parameters.size() > 0) {
            append(open, parameters.get(0));

            for (int i = 1; i < parameters.size(); i++) {
                append(comma, space, parameters.get(i));
            }

            append(close);
        }

        return write();
    }

    @Override
    public AnnotationParameter withAnnotationClass(Class<?> annotationClass) {
        this.annotationClass = annotationClass;
        return this;
    }

    @Override
    public AnnotationWithParameter withParameters() {
        return this;
    }

    @Override
    public Builder<Annotation> withoutParameters() {
        return this;
    }

    @Override
    public AnnotationParameter addParameter(String parameter) {
        parameters.add(parameter);
        return this;
    }

    @Override
    public Builder<Annotation> addLastParameter(String parameter) {
        parameters.add(parameter);
        return this;
    }

    @Override
    public Annotation build() {
        return this;
    }
}
