package de.tbd.codegeneratorutils._interface;

import de.tbd.codegeneratorutils.Builder;
import de.tbd.codegeneratorutils.Pair;
import de.tbd.codegeneratorutils.method.Method;

import java.util.*;

public class Interface implements InterfaceClass, InterfaceMethod, Builder<Interface> {

    private Class<?> _interface;

    private final List<Method> methods = new ArrayList<>();

    private Interface() {

    }

    public static InterfaceClass newInterface() {
        return new Interface();
    }

    @Override
    public InterfaceMethod ofInterface(Class<?> _interface) {
        this._interface = _interface;
        return this;
    }

    @Override
    public Builder<Interface> withoutMethod() {
        return this;
    }

    @Override
    public Builder<Interface> withMethod(Pair<java.lang.reflect.Method, String> method) {
        methods.add(Method.mapMethod(method.getKey(), method.getValue()));
        return this;
    }

    @Override
    public Builder<Interface> withMethods(List<Pair<java.lang.reflect.Method, String>> methods) {
        methods.forEach(m -> this.methods.add(Method.mapMethod(m.getKey(), m.getValue())));
        return this;
    }

    @Override
    public Interface build() {
        return this;
    }

    public Class<?> getInterface() {
        return _interface;
    }

    public List<Method> getMethods() {
        return methods;
    }
}
