package de.tbd.codegeneratorutils.method;

import de.tbd.codegeneratorutils.annotation.Annotation;

public interface MethodAnnotation {

    MethodWithAnnotation withAnnotations();

    MethodModifier withoutAnnotations();

    interface MethodWithAnnotation {

        MethodWithAnnotation addAnnotation(Annotation annotation);

        MethodModifier addLastAnnotation(Annotation annotation);
    }

}
