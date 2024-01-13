package de.tbd.codegeneratorutils.clazz;

import de.tbd.codegeneratorutils.Builder;
import de.tbd.codegeneratorutils.method.Method;

public interface ClassMethod {

    ClassWithMethod withMethods();

    Builder<Class> withoutMethods();

    interface ClassWithMethod {

        ClassWithMethod addMethod(Method method);

        Builder<Class> addLastMethod(Method method);

    }
}
