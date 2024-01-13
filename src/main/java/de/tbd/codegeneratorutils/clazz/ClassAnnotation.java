package de.tbd.codegeneratorutils.clazz;

import de.tbd.codegeneratorutils.annotation.Annotation;

public interface ClassAnnotation {

    ClassWithAnnotation withAnnotation();

    ClassClassName withoutAnnotation();

    interface ClassWithAnnotation {

        ClassWithAnnotation addAnnotation(Annotation annotation);

        ClassClassName addLastAnnotation(Annotation annotation);

    }
}
