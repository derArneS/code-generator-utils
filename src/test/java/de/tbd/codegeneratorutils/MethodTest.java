package de.tbd.codegeneratorutils;

import de.tbd.codegeneratorutils.method.Method;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MethodTest {

    @Test
    public void simpleMethodTest() {
        Method testee = Method.method()
                .withoutAnnotations()
                .withPublicModifier()
                .withReturnType(Void.class)
                .withName("test")
                .withoutParameters()
                .withCode("return \"hello world\"")
                .build();

        final String expected = """
                public java.lang.Void test() {
                    return "hello world";
                }""";
        assertEquals(expected, testee.getCodeAsString());
    }
}
