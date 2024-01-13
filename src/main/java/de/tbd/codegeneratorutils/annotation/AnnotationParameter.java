package de.tbd.codegeneratorutils.annotation;

import de.tbd.codegeneratorutils.Builder;

public interface AnnotationParameter {

    AnnotationWithParameter withParameters();

    Builder<Annotation> withoutParameters();

    interface AnnotationWithParameter {

        AnnotationParameter addParameter(String parameter);

        Builder<Annotation> addLastParameter(String parameter);
    }

}
