package de.tbd.codegeneratorutils.clazz;

import java.util.List;

public interface ClassSuperClass {

    ClassInterface withoutSuperClass();

    ClassInterface withSuperClass(java.lang.Class<?> superClass);

    ClassInterface withSuperClasses(List<java.lang.Class<?>> superClasses);

}
