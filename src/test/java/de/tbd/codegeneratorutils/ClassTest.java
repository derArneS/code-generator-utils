package de.tbd.codegeneratorutils;

import de.tbd.codegeneratorutils._interface.Interface;
import de.tbd.codegeneratorutils._class.Class;
import de.tbd.codegeneratorutils.testclasses.TestInterface;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClassTest {

    @Test
    public void simpleClassTest() {
        String testString = Class.newClass()
                .withPackage("de.tbd.codegeneratorutils")
                .withoutAnnotation()
                .withName("GeneratedTestClass")
                .withoutSuperClass()
                .withoutInterface()
                .withoutMethod()
                .withoutOverrideCode()
                .build()
                .getCodeAsString();

        String expected = """
                package de.tbd.codegeneratorutils;
                                
                public class GeneratedTestClass {
                                
                }""";

        assertEquals(expected, testString);
    }

    @Test
    public void classWithSingleInterfaceTest() throws NoSuchMethodException {
        Interface testInterface = Interface.newInterface()
                .ofInterface(TestInterface.class)
                .withMethods(
                        List.of(
                                new Pair<>(TestInterface.class.getMethod("secondTestMethod", String.class, int.class), null),
                                new Pair<>(TestInterface.class.getMethod("testMethod", String.class), null)
                        )
                )
                .build();

        String testString = Class.newClass()
                .withPackage("de.tbd.codegeneratorutils")
                .withoutAnnotation()
                .withName("GeneratedTestClass")
                .withoutSuperClass()
                .withInterface(testInterface)
                .withoutMethod()
                .withoutOverrideCode()
                .build()
                .getCodeAsString();

        String expected = """
                package de.tbd.codegeneratorutils;
                                
                public class GeneratedTestClass implements de.tbd.codegeneratorutils.testclasses.TestInterface {
                                
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
