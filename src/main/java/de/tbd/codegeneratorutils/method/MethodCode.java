package de.tbd.codegeneratorutils.method;

import de.tbd.codegeneratorutils.Builder;

public interface MethodCode {

    Builder<Method> withCode(String code);
}
