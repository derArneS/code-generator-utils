package de.tbd.codegeneratorutils._class;

import de.tbd.codegeneratorutils.annotation.Annotation;

import java.util.List;

public interface ClassAnnotation {

    ClassClassName withoutAnnotation();

    ClassClassName withAnnotation(Annotation annotation);

    ClassClassName withAnnotations(List<Annotation> annotation);

}
