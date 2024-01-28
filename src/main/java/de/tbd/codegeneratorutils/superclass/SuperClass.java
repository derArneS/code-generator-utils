package de.tbd.codegeneratorutils.superclass;

import de.tbd.codegeneratorutils.Builder;
import de.tbd.codegeneratorutils.Pair;
import de.tbd.codegeneratorutils.method.Method;

import java.util.ArrayList;
import java.util.List;

public class SuperClass implements SuperClassClass, SuperClassMethod, Builder<SuperClass> {

    private Class<?> superClass;

    private final List<Method> methods = new ArrayList<>();

    private SuperClass() {

    }

    public static SuperClassClass newSuperClass() {
        return new SuperClass();
    }

    @Override
    public SuperClassMethod ofClass(Class<?> superClass) {
        this.superClass = superClass;
        return this;
    }

    @Override
    public Builder<SuperClass> withoutMethod() {
        return this;
    }

    @Override
    public Builder<SuperClass> withMethod(Pair<java.lang.reflect.Method, String> method) {
        methods.add(Method.mapMethod(method.getKey(), method.getValue()));
        return this;
    }

    @Override
    public Builder<SuperClass> withMethods(List<Pair<java.lang.reflect.Method, String>> methods) {
        methods.forEach(m -> this.methods.add(Method.mapMethod(m.getKey(), m.getValue())));
        return this;
    }

    @Override
    public SuperClass build() {
        return this;
    }

    public Class<?> getSuperClass() {
        return superClass;
    }

    public List<Method> getMethods() {
        return methods;
    }
}
