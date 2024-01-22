package de.tbd.codegeneratorutils.clazz;

import java.util.List;

public interface ClassInterface {

    ClassMethod withoutInterfaces();

    ClassMethod withInterface(java.lang.Class<?> _interface);

    ClassMethod withInterfaces(List<java.lang.Class<?>> interfaces);
}
