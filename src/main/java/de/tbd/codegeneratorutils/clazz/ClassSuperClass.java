package de.tbd.codegeneratorutils.clazz;

public interface ClassSuperClass {

    ClassWithSuperClasses withSuperClasses();

    ClassInterface withoutSuperClasses();

    interface ClassWithSuperClasses {

        ClassWithSuperClasses addSuperClass(java.lang.Class<?> superClass);

        ClassInterface addLastSuperClass(java.lang.Class<?> superClass);

    }
}
