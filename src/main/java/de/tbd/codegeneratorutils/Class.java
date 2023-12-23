package de.tbd.codegeneratorutils;

import java.lang.reflect.Parameter;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Class extends AbstractCodable {

    private String _package;

    private final List<java.lang.Class<?>> imports = new ArrayList<>();

    private final List<String> classAnnotations = new ArrayList<>();

    private String className;

    private final List<java.lang.Class<?>> superClasses = new ArrayList<>();

    private final List<java.lang.Class<?>> interfaces = new ArrayList<>();

    public Class setPackage(String _package) {
        this._package = _package;
        return this;
    }

    public Class addImport(java.lang.Class<?> import1) {
        imports.add(import1);
        return this;
    }

    public Class addClassAnnotation(String annotation) {
        classAnnotations.add(annotation);
        return this;
    }

    public Class setClassName(String className) {
        this.className = className;
        return this;
    }

    public Class addSuperClass(java.lang.Class<?> superClass) {
        superClasses.add(superClass);
        return this;
    }

    public Class addInterface(java.lang.Class<?> interface1) {
        interfaces.add(interface1);
        return this;
    }

    public String getCodeAsString() {
        writePackage();
        if (!imports.isEmpty()) writeImports();
        if (!classAnnotations.isEmpty()) writeClassAnnotations();

        append("public class ", className, space);

        if (!superClasses.isEmpty()) writeSuperClasses();
        if (!interfaces.isEmpty()) writeInterfaces();

        append(curlyOpen, newLine, newLine);

        if (!interfaces.isEmpty()) implementInterfaces();

        append(curlyClose);
        return sb.toString();
    }

    private void writePackage() {
        append("package ", _package, semicolon, newLine);
        append(newLine);
    }

    private void writeImports() {
        for (java.lang.Class<?> import1 : imports) {
            append("import ", import1.getName(), semicolon, newLine);
        }

        append(newLine);
    }

    private void writeClassAnnotations() {
        for (String annotation : classAnnotations) {
            append(at, annotation, semicolon);
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

    private void implementInterfaces() {
        for (java.lang.Class<?> interface1 : interfaces) {
            java.lang.reflect.Method[] methods = interface1.getMethods();

            Arrays.sort(methods, (o1, o2) ->
                    Collator.getInstance().compare(o1.getName(), o2.getName()));

            for (java.lang.reflect.Method method : methods) {
                append(1, "@Override", newLine);
                append(1, Utils.getModifierAsString(method.getModifiers()));
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

}
