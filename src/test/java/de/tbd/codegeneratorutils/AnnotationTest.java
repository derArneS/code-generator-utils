package de.tbd.codegeneratorutils;

import de.tbd.codegeneratorutils.annotation.Annotation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnnotationTest {

    @Test
    public void simpleAnnotationTest() {
        final Annotation testee = Annotation.annotation()
                .withAnnotationClass(Test.class)
                .withoutParameters()
                .build();

        final String expected = "@org.junit.jupiter.api.Test";
        assertEquals(expected, testee.getCodeAsString());
    }
}
