package de.tbd.codegeneratorutils.method;

import de.tbd.codegeneratorutils.Pair;

public interface MethodParameter {

    MethodWithParameter withParameters();

    MethodCode withoutParameters();

    interface MethodWithParameter {

        MethodWithParameter addParameter(Pair<Class<?>, String> parameter);

        MethodCode addLastParameter(Pair<Class<?>, String> parameter);

    }
}
