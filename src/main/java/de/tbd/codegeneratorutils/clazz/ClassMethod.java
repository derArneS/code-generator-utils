package de.tbd.codegeneratorutils.clazz;

import de.tbd.codegeneratorutils.Builder;
import de.tbd.codegeneratorutils.method.Method;

import java.util.List;

public interface ClassMethod {

    Builder<Class> withoutMethod();

    Builder<Class> withMethod(Method method);

    Builder<Class> withMethods(List<Method> methods);

}
