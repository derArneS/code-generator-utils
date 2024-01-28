package de.tbd.codegeneratorutils._interface;

import de.tbd.codegeneratorutils.Builder;
import de.tbd.codegeneratorutils.Pair;

import java.lang.reflect.Method;
import java.util.List;

public interface InterfaceMethod {

    Builder<Interface> withoutMethod();

    Builder<Interface> withMethod(Pair<Method, String> method);

    Builder<Interface> withMethods(List<Pair<Method, String>> methods);
}
