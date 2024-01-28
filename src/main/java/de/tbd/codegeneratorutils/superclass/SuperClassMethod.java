package de.tbd.codegeneratorutils.superclass;

import de.tbd.codegeneratorutils.Builder;
import de.tbd.codegeneratorutils.Pair;

import java.lang.reflect.Method;
import java.util.List;

public interface SuperClassMethod {

    Builder<SuperClass> withoutMethod();

    Builder<SuperClass> withMethod(Pair<Method, String> method);

    Builder<SuperClass> withMethods(List<Pair<Method, String>> methods);
}
