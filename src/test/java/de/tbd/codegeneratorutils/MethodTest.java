package de.tbd.codegeneratorutils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MethodTest {

    @Test
    public void simpleMethodTest() {
        MethodDesc testee = MethodDesc.methodBuilder("test")
                .addModifier(Modifier.PUBLIC)
                .addLineOfCode("return \"hello world\";")
                .build();

        final String expected = """
                public void test() {
                    return "hello world";
                }
                """;

        CodeWriter codeWriter = new CodeWriter();
        codeWriter.append(testee);
        assertEquals(expected, codeWriter.print());
    }
}
