package de.tbd.codegeneratorutils._class;

import de.tbd.codegeneratorutils.superclass.SuperClass;

import java.util.List;

public interface ClassSuperClass {

    ClassInterface withoutSuperClass();

    ClassInterface withSuperClass(SuperClass superClass);

    ClassInterface withSuperClasses(List<SuperClass> superClasses);

}
