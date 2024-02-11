package de.tbd.codegeneratorutils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnnotationDescTest {

    @Test
    public void simpleAnnotationTest() {
        final AnnotationDesc testee = AnnotationDesc.annotationBuilder(Test.class)
                .build();

        final String expected = "@org.junit.jupiter.api.Test\n";
        final CodeWriter codeWriter = new CodeWriter();
        codeWriter.append(testee);
        assertEquals(expected, codeWriter.print());
    }

    @Test
    public void annotationWithSingleValueTest() {
        final AnnotationDesc testee = AnnotationDesc.annotationBuilder(FirstTestAnno.class)
                .addParameter("value", "\"testValue\"")
                .build();

        final String expected = "@" + AnnotationDescTest.class.getName() + ".FirstTestAnno(\"testValue\")\n";
        final CodeWriter codeWriter = new CodeWriter();
        codeWriter.append(testee);
        assertEquals(expected, codeWriter.print());
    }

    @Test
    public void annotationWithMultipleValuesInclArrayTest() {
        final AnnotationDesc testee = AnnotationDesc.annotationBuilder(SecondTestAnno.class)
                .addParameter("array", "\"first\"", "\"second\"")
                .addParameter("string", "\"string\"")
                .build();

        final String expectedPossibilityOne = "@" + AnnotationDescTest.class.getName() + """
                .SecondTestAnno(array = {"first", "second"}, string = "string")
                """;
        final String expectedPossibilityTwo = "@" + AnnotationDescTest.class.getName() + """
                .SecondTestAnno(string = "string", array = {"first", "second"})
                """;

        final CodeWriter codeWriter = new CodeWriter();
        codeWriter.append(testee);
        final String actual = codeWriter.print();
        assertTrue(expectedPossibilityOne.equals(actual) || expectedPossibilityTwo.equals(actual));
    }

    private @interface FirstTestAnno {
        String value();
    }

    private @interface SecondTestAnno {
        String[] array();

        String string();
    }
}
