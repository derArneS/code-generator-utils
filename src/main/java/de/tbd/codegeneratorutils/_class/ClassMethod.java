package de.tbd.codegeneratorutils._class;

import de.tbd.codegeneratorutils.method.Method;

import java.util.List;

public interface ClassMethod {

    ClassCode withoutMethod();

    ClassCode withMethod(Method method);

    ClassCode withMethods(List<Method> methods);

}
