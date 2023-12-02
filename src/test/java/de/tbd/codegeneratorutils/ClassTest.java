package de.tbd.codegeneratorutils;

import de.tbd.codegeneratorutils.testclasses.SecondClassForImport;
import de.tbd.codegeneratorutils.testclasses.TestClassForImport;
import de.tbd.codegeneratorutils.testclasses.TestInterface;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClassTest {

    @Test
    public void simpleClassTest() {
        String testString = new Class()
                .setPackage("de.tbd.codegeneratorutils")
                .setClassName("GeneratedTestClass")
                .getCodeAsString();

        String expected = """
                package de.tbd.codegeneratorutils;
                                
                public class GeneratedTestClass {
                                
                }""";

        assertEquals(expected, testString);
    }

    @Test
    public void classWithSingleImportTest() {
        String testString = new Class()
                .setPackage("de.tbd.codegeneratorutils")
                .addImport(TestClassForImport.class)
                .setClassName("GeneratedTestClass")
                .getCodeAsString();

        String expected = """
                package de.tbd.codegeneratorutils;
                                
                import de.tbd.codegeneratorutils.testclasses.TestClassForImport;
                                
                public class GeneratedTestClass {
                                
                }""";

        assertEquals(expected, testString);
    }

    @Test
    public void classWithMultipleImportsTest() {
        String testString = new Class()
                .setPackage("de.tbd.codegeneratorutils")
                .addImport(TestClassForImport.class)
                .addImport(SecondClassForImport.class)
                .setClassName("GeneratedTestClass")
                .getCodeAsString();

        String expected = """
                package de.tbd.codegeneratorutils;
                                
                import de.tbd.codegeneratorutils.testclasses.TestClassForImport;
                import de.tbd.codegeneratorutils.testclasses.SecondClassForImport;
                                
                public class GeneratedTestClass {
                                
                }""";

        assertEquals(expected, testString);
    }

    @Test
    public void classWithSingleInterfaceTest() {
        String testString = new Class()
                .setPackage("de.tbd.codegeneratorutils")
                .setClassName("GeneratedTestClass")
                .addInterface(TestInterface.class)
                .getCodeAsString();

        String expected = """
                package de.tbd.codegeneratorutils;
                                
                public class GeneratedTestClass implements TestInterface {
                                
                    @Override
                    public boolean secondTestMethod(String arg0, int arg1) {
                    }
                            
                    @Override
                    public String testMethod(String arg0) {
                    }
                                
                }""";

        assertEquals(expected, testString);
    }
}
