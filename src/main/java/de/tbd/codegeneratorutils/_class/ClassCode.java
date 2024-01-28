package de.tbd.codegeneratorutils._class;

import de.tbd.codegeneratorutils.Builder;

import java.util.Map;

public interface ClassCode {

    Builder<Class> withoutOverrideCode();

    Builder<Class> withOverrideCode(String methodName, String code);

    Builder<Class> withOverrideCode(Map<String, String> code);
}
