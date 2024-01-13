package de.tbd.codegeneratorutils.clazz;

public interface ClassInterface {

    ClassWithInterface withInterfaces();

    ClassMethod withoutInterfaces();

    interface ClassWithInterface {

        ClassWithInterface addInterface(java.lang.Class<?> interface1);

        ClassMethod addLastInterface(java.lang.Class<?> interface1);

    }
}
