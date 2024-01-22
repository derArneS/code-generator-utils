package de.tbd.codegeneratorutils.clazz;

import de.tbd.codegeneratorutils.*;
import de.tbd.codegeneratorutils.annotation.Annotation;
import de.tbd.codegeneratorutils.method.Method;

import java.lang.reflect.Parameter;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Class extends AbstractCodable implements Builder<Class>, ClassMethod, ClassInterface, ClassSuperClass,
        ClassClassName, ClassAnnotation, ClassPackage {

    private String _package;

    private final List<Annotation> classAnnotations = new ArrayList<>();

    private String className;

    private final List<java.lang.Class<?>> superClasses = new ArrayList<>();

    private final List<java.lang.Class<?>> interfaces = new ArrayList<>();

    private final List<Method> methods = new ArrayList<>();

    private Class() {

    }

    public static ClassPackage newClass() {
        return new Class();
    }

    private void extractMethodsFromInterfaces() {
        for (java.lang.Class<?> interface1 : interfaces) {
            java.lang.reflect.Method[] methods = interface1.getMethods();

            Arrays.sort(methods, (o1, o2) ->
                    Collator.getInstance().compare(o1.getName(), o2.getName()));

            this.methods.addAll(Stream.of(methods).map(this::mapMethod).toList());
        }
    }

    private Method mapMethod(java.lang.reflect.Method m) {
        return Method.method()
                .withAnnotations()
                .addLastAnnotation(Annotation.annotation()
                        .withAnnotationClass(Override.class)
                        .withoutParameters()
                        .build()
                )
                .withModifier(Utils.getModifier(m.getModifiers()))
                .withReturnType(m.getReturnType())
                .withName(m.getName())
                .withParameters(() -> {
                    Parameter[] p = m.getParameters();
                    List<Pair<java.lang.Class<?>, String>> parameters = new ArrayList<>();
                    for (int i = 0; i < p.length; i++)
                        parameters.add(new Pair<>(p[i].getType(), "arg" + i));
                    return parameters;
                })
                .withCode(null)
                .build();
    }

    @Override
    public String getCodeAsString(int tabLevel) {
        writePackage();
        if (!classAnnotations.isEmpty()) writeClassAnnotations();

        append("public class ", className, space);

        if (!superClasses.isEmpty()) writeSuperClasses();
        if (!interfaces.isEmpty()) writeInterfaces();

        append(curlyOpen, newLine, newLine);

        methods.forEach(m -> append(m.getCodeAsString(tabLevel + 1), newLine));

        append(curlyClose);
        return write();
    }

    private void writePackage() {
        append("package ", _package, semicolon, newLine);
        append(newLine);
    }

    private void writeClassAnnotations() {
        for (Annotation annotation : classAnnotations) {
            append(at, annotation.getCodeAsString());
        }
    }

    private void writeSuperClasses() {
        append("extends ", superClasses.get(0).getSimpleName());

        for (int i = 1; i < superClasses.size(); i++) {
            append(", ", superClasses.get(i).getSimpleName());
        }

        append(space);
    }

    private void writeInterfaces() {
        append("implements ", interfaces.get(0).getSimpleName());

        for (int i = 1; i < interfaces.size(); i++) {
            append(", ", interfaces.get(i).getSimpleName());
        }

        append(space);
    }

    @Override
    public ClassAnnotation withPackage(String _package) {
        this._package = _package;
        return this;
    }

    @Override
    public ClassClassName withoutAnnotation() {
        return this;
    }


    @Override
    public ClassClassName withAnnotation(Annotation annotation) {
        classAnnotations.add(annotation);
        return this;
    }

    @Override
    public ClassClassName withAnnotations(List<Annotation> annotation) {
        classAnnotations.addAll(annotation);
        return this;
    }

    @Override
    public ClassSuperClass withName(String className) {
        this.className = className;
        return this;
    }

    @Override
    public ClassInterface withoutSuperClass() {
        return this;
    }

    @Override
    public ClassInterface withSuperClass(java.lang.Class<?> superClass) {
        superClasses.add(superClass);
        return this;
    }

    @Override
    public ClassInterface withSuperClasses(List<java.lang.Class<?>> superClasses) {
        this.superClasses.addAll(superClasses);
        return this;
    }

    @Override
    public ClassMethod withoutInterfaces() {
        return this;
    }

    @Override
    public ClassMethod withInterface(java.lang.Class<?> _interface) {
        interfaces.add(_interface);
        return this;
    }

    @Override
    public ClassMethod withInterfaces(List<java.lang.Class<?>> interfaces) {
        this.interfaces.addAll(interfaces);
        return this;
    }

    @Override
    public Builder<Class> withoutMethod() {
        return this;
    }

    @Override
    public Builder<Class> withMethod(Method method) {
        methods.add(method);
        return this;
    }

    @Override
    public Builder<Class> withMethods(List<Method> methods) {
        this.methods.addAll(methods);
        return this;
    }

    @Override
    public Class build() {
        extractMethodsFromInterfaces();
        return this;
    }

}
