package de.tbd.codegeneratorutils;

import de.tbd.codegeneratorutils.clazz.Class;
import de.tbd.codegeneratorutils.testclasses.TestInterface;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClassTest {

    @Test
    public void simpleClassTest() {
        String testString = Class.newClass()
                .withPackage("de.tbd.codegeneratorutils")
                .withoutAnnotation()
                .withName("GeneratedTestClass")
                .withoutSuperClass()
                .withoutInterfaces()
                .withoutMethod()
                .build()
                .getCodeAsString();

        String expected = """
                package de.tbd.codegeneratorutils;
                                
                public class GeneratedTestClass {
                                
                }""";

        assertEquals(expected, testString);
    }

    @Test
    public void classWithSingleInterfaceTest() {
        String testString = Class.newClass()
                .withPackage("de.tbd.codegeneratorutils")
                .withoutAnnotation()
                .withName("GeneratedTestClass")
                .withoutSuperClass()
                .withInterface(TestInterface.class)
                .withoutMethod()
                .build()
                .getCodeAsString();

        String expected = """
                package de.tbd.codegeneratorutils;
                                
                public class GeneratedTestClass implements TestInterface {
                                
                    @java.lang.Override
                    public boolean secondTestMethod(java.lang.String arg0, int arg1) {
                    }
                            
                    @java.lang.Override
                    public java.lang.String testMethod(java.lang.String arg0) {
                    }
                                
                }""";

        assertEquals(expected, testString);
    }
}
