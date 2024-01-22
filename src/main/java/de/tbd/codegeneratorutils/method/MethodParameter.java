package de.tbd.codegeneratorutils.method;

import de.tbd.codegeneratorutils.Pair;

import java.util.List;

public interface MethodParameter {

    MethodCode withoutParameters();

    MethodCode withParameter(Pair<Class<?>, String> parameter);

    MethodCode withParameters(List<Pair<Class<?>, String>> parameter);

    MethodCode withParameters(MethodParameterContainer container);

    interface MethodParameterContainer {

        List<Pair<Class<?>, String>> getParameters();
    }

}
