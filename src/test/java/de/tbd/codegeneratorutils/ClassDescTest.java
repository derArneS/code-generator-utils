package de.tbd.codegeneratorutils;

import de.tbd.codegeneratorutils.testclasses.TestInterface;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClassDescTest {

    @Test
    public void simpleClassTest() {
        String testString = ClassDesc.classBuilder("GeneratedTestClass")
                .setPackage("de.tbd.codegeneratorutils")
                .build()
                .print();

        String expected = """
                package de.tbd.codegeneratorutils;
                                
                public class GeneratedTestClass {
                                
                }""";

        assertEquals(expected, testString);
    }

    @Test
    public void classWithSingleInterfaceTest() {
        final String testString = ClassDesc.classBuilder("GeneratedTestClass")
                .setPackage("de.tbd.codegeneratorutils")
                .addSuperInterface(TestInterface.class)
                .addMethod(MethodDesc.methodBuilder("secondTestMethod")
                        .addAnnotation(AnnotationDesc.annotationBuilder(Override.class).build())
                        .addModifier(Modifier.PUBLIC)
                        .returns(boolean.class)
                        .addParameter("arg0", String.class)
                        .addParameter("arg1", int.class)
                        .build())
                .addMethod(MethodDesc.methodBuilder("testMethod")
                        .addAnnotation(AnnotationDesc.annotationBuilder(Override.class).build())
                        .addModifier(Modifier.PUBLIC)
                        .returns(String.class)
                        .addParameter("arg0", String.class)
                        .build())
                .build()
                .print();

        final String expected = """
                package de.tbd.codegeneratorutils;
                                
                public class GeneratedTestClass implements de.tbd.codegeneratorutils.testclasses.TestInterface {
                                
                    @java.lang.Override
                    public boolean secondTestMethod(java.lang.String arg0, int arg1) {
                    }
                            
                    @java.lang.Override
                    public java.lang.String testMethod(java.lang.String arg0) {
                    }
                                
                }""";

        final String expected2 = """
                package de.tbd.codegeneratorutils;
                                
                public class GeneratedTestClass implements de.tbd.codegeneratorutils.testclasses.TestInterface {
                                
                    @java.lang.Override
                    public boolean secondTestMethod(int arg1, java.lang.String arg0) {
                    }
                            
                    @java.lang.Override
                    public java.lang.String testMethod(java.lang.String arg0) {
                    }
                                
                }""";

        System.out.println(testString);
        assertTrue(expected.equals(testString) || expected2.equals(testString));
    }
}
