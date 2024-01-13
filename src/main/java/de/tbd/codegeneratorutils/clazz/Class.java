package de.tbd.codegeneratorutils.clazz;

import de.tbd.codegeneratorutils.AbstractCodable;
import de.tbd.codegeneratorutils.Builder;
import de.tbd.codegeneratorutils.annotation.Annotation;
import de.tbd.codegeneratorutils.method.Method;

import java.util.ArrayList;
import java.util.List;

public class Class extends AbstractCodable implements Builder<Class>, ClassMethod, ClassMethod.ClassWithMethod, ClassInterface,
        ClassInterface.ClassWithInterface, ClassSuperClass, ClassSuperClass.ClassWithSuperClasses,
        ClassClassName, ClassAnnotation, ClassAnnotation.ClassWithAnnotation, ClassPackage {

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

    public String getCodeAsString(int tabLevel) {
        writePackage();
        if (!classAnnotations.isEmpty()) writeClassAnnotations();

        append("public class ", className, space);

        if (!superClasses.isEmpty()) writeSuperClasses();
        if (!interfaces.isEmpty()) writeInterfaces();

        append(curlyOpen, newLine, newLine);

        //if (!interfaces.isEmpty()) implementInterfaces();

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

    /*
    private void implementInterfaces() {
        for (java.lang.Class<?> interface1 : interfaces) {
            java.lang.reflect.Method[] methods = interface1.getMethods();

            Arrays.sort(methods, (o1, o2) ->
                    Collator.getInstance().compare(o1.getName(), o2.getName()));

            for (java.lang.reflect.Method method : methods) {
                append(1, "@Override", newLine);
                append(1, Utils.getModifier(method.getModifiers()).asString);
                append(space, method.getReturnType().getSimpleName());
                append(space, method.getName(), open);

                Parameter[] parameters = method.getParameters();

                if (0 < parameters.length) {
                    append(parameters[0].getType().getSimpleName(), space, "arg0");

                    for (int i = 1; i < parameters.length; i++) {
                        append(", ", parameters[i].getType().getSimpleName(), space, "arg", String.valueOf(i));
                    }
                }

                append(close, space, curlyOpen, newLine);

                append(1, curlyClose, newLine, newLine);
            }
        }
    }
     */

    @Override
    public ClassAnnotation withPackage(String _package) {
        this._package = _package;
        return this;
    }

    @Override
    public ClassWithAnnotation withAnnotation() {
        return this;
    }

    @Override
    public ClassClassName withoutAnnotation() {
        return this;
    }

    @Override
    public ClassWithAnnotation addAnnotation(Annotation annotation) {
        classAnnotations.add(annotation);
        return this;
    }

    @Override
    public ClassClassName addLastAnnotation(Annotation annotation) {
        classAnnotations.add(annotation);
        return this;
    }

    @Override
    public ClassSuperClass withName(String className) {
        this.className = className;
        return this;
    }

    @Override
    public ClassWithSuperClasses withSuperClasses() {
        return this;
    }

    @Override
    public ClassInterface withoutSuperClasses() {
        return this;
    }

    @Override
    public ClassWithSuperClasses addSuperClass(java.lang.Class<?> superClass) {
        superClasses.add(superClass);
        return this;
    }

    @Override
    public ClassInterface addLastSuperClass(java.lang.Class<?> superClass) {
        superClasses.add(superClass);
        return this;
    }

    @Override
    public ClassWithInterface withInterfaces() {
        return this;
    }

    @Override
    public ClassMethod withoutInterfaces() {
        return this;
    }

    @Override
    public ClassWithInterface addInterface(java.lang.Class<?> _interface) {
        interfaces.add(_interface);
        return this;
    }

    @Override
    public ClassMethod addLastInterface(java.lang.Class<?> _interface) {
        interfaces.add(_interface);
        return this;
    }

    @Override
    public ClassWithMethod withMethods() {
        return this;
    }

    @Override
    public Builder<Class> withoutMethods() {
        return this;
    }

    @Override
    public ClassWithMethod addMethod(Method method) {
        methods.add(method);
        return this;
    }

    @Override
    public Builder<Class> addLastMethod(Method method) {
        methods.add(method);
        return this;
    }

    @Override
    public Class build() {
        return this;
    }

}
