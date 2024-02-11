package de.tbd.codegeneratorutils;

import java.util.*;

public class ClassDesc implements StringConstants {

    public static Builder classBuilder(String className) {
        return new Builder(className);
    }

    private final CodeWriter codeWriter = new CodeWriter();

    private String packageName;

    private final List<AnnotationDesc> annotations = new ArrayList<>();

    private final String className;

    private final List<Class<?>> superClasses = new ArrayList<>();

    private final List<String> superInterfaces = new ArrayList<>();

    private final List<MethodDesc> methods = new ArrayList<>();

    private ClassDesc(String className) {
        this.className = className;
    }

    public String print() {
        if (packageName != null) writePackage();
        for (AnnotationDesc annotation : annotations) {
            codeWriter.append(annotation);
        }

        codeWriter.append("public class ", className, space);

        if (!superClasses.isEmpty()) writeSuperClasses();
        if (!superInterfaces.isEmpty()) writeInterfaces();

        codeWriter.append(curlyOpen, newLine, newLine);

        methods.forEach(method -> {
            codeWriter.indent();
            codeWriter.append(method);
            codeWriter.append(newLine);
            codeWriter.unindent();
        });

        codeWriter.append(curlyClose);
        return codeWriter.print();
    }

    private void writePackage() {
        codeWriter.append("package ", packageName, semicolon, newLine, newLine);
    }

    private void writeSuperClasses() {
        for (int i = 0; i < superClasses.size(); i++) {
            if (i == 0) {
                codeWriter.append("extends ", superClasses.get(i).getName());
            } else {
                codeWriter.append(", ", superClasses.get(i).getName());
            }
        }

        codeWriter.append(space);
    }

    private void writeInterfaces() {
        for (int i = 0; i < superInterfaces.size(); i++) {
            if (i == 0) {
                codeWriter.append("implements ", superInterfaces.get(i));
            } else {
                codeWriter.append(", ", superInterfaces.get(i));
            }
        }

        codeWriter.append(space);
    }

    public String getPackage() {
        return packageName;
    }

    public String getClassName() {
        return className;
    }

    public String getClassNameWithPackage() {
        if (packageName == null || "".equals(packageName)) {
            return className;
        }

        return packageName + "." + className;
    }

    public static class Builder {

        private final ClassDesc classDesc;

        private Builder(String className) {
            classDesc = new ClassDesc(className);
        }

        public Builder setPackage(String packageName) {
            classDesc.packageName = packageName;
            return this;
        }

        public Builder addAnnotation(AnnotationDesc annotation) {
            classDesc.annotations.add(annotation);
            return this;
        }

        public Builder addSuperClass(Class<?> superClass) {
            classDesc.superClasses.add(superClass);
            return this;
        }

        public Builder addSuperInterface(Class<?> superInterface) {
            classDesc.superInterfaces.add(superInterface.getName());
            return this;
        }

        public Builder addSuperInterface(String superInterfaceName) {
            classDesc.superInterfaces.add(superInterfaceName);
            return this;
        }

        public Builder addMethod(MethodDesc method) {
            classDesc.methods.add(method);
            return this;
        }

        public Builder addMethods(List<MethodDesc> methods) {
            classDesc.methods.addAll(methods);
            return this;
        }

        public ClassDesc build() {
            return classDesc;
        }
    }
}
