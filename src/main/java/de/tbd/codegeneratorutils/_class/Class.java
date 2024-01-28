package de.tbd.codegeneratorutils._class;

import de.tbd.codegeneratorutils.*;
import de.tbd.codegeneratorutils._interface.Interface;
import de.tbd.codegeneratorutils.annotation.Annotation;
import de.tbd.codegeneratorutils.method.Method;
import de.tbd.codegeneratorutils.superclass.SuperClass;

import java.util.*;

public class Class extends AbstractCodable implements Builder<Class>, ClassMethod, ClassInterface, ClassSuperClass,
        ClassClassName, ClassAnnotation, ClassPackage, ClassCode {

    private String _package;

    private final List<Annotation> classAnnotations = new ArrayList<>();

    private String className;

    private final List<SuperClass> superClasses = new ArrayList<>();

    private final List<Interface> interfaces = new ArrayList<>();

    private final List<Method> methods = new ArrayList<>();

    private Class() {

    }

    public static ClassPackage newClass() {
        return new Class();
    }

    @Override
    public String getCodeAsString(int tabLevel) {
        writePackage();
        if (!classAnnotations.isEmpty()) writeClassAnnotations();

        append("public class ", className, space);

        if (!superClasses.isEmpty()) writeSuperClasses();
        if (!interfaces.isEmpty()) writeInterfaces();

        append(curlyOpen, newLine, newLine);

        for (Interface _interface : interfaces) {
            _interface.getMethods().forEach(m -> append(m.getCodeAsString(tabLevel + 1), newLine));
        }
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
        append("extends ", superClasses.get(0).getSuperClass().getSimpleName());

        for (int i = 1; i < superClasses.size(); i++) {
            append(", ", superClasses.get(i).getSuperClass().getSimpleName());
        }

        append(space);
    }

    private void writeInterfaces() {
        append("implements ", interfaces.get(0).getInterface().getName());

        for (int i = 1; i < interfaces.size(); i++) {
            append(", ", interfaces.get(i).getInterface().getName());
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
    public ClassInterface withSuperClass(SuperClass superClass) {
        superClasses.add(superClass);
        return this;
    }

    @Override
    public ClassInterface withSuperClasses(List<SuperClass> superClasses) {
        this.superClasses.addAll(superClasses);
        return this;
    }

    @Override
    public ClassMethod withoutInterface() {
        return this;
    }

    @Override
    public ClassMethod withInterface(Interface _interface) {
        interfaces.add(_interface);
        return this;
    }

    @Override
    public ClassMethod withInterfaces(List<Interface> interfaces) {
        this.interfaces.addAll(interfaces);
        return this;
    }

    @Override
    public ClassCode withoutMethod() {
        return this;
    }

    @Override
    public ClassCode withMethod(Method method) {
        methods.add(method);
        return this;
    }

    @Override
    public ClassCode withMethods(List<Method> methods) {
        this.methods.addAll(methods);
        return this;
    }

    @Override
    public Builder<Class> withoutOverrideCode() {
        return this;
    }

    @Override
    public Builder<Class> withOverrideCode(String methodName, String code) {
        throw new IllegalStateException("not implemented");
        //TODO code von richtiger Methode mit richtigen Parametern überschreiben
        //return this;
    }

    @Override
    public Builder<Class> withOverrideCode(Map<String, String> code) {
        throw new IllegalStateException("not implemented");
        //TODO code von richtiger Methode mit richtigen Parametern überschreiben
        //return this;
    }

    @Override
    public Class build() {
        return this;
    }

}
