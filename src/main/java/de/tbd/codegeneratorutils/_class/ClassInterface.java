package de.tbd.codegeneratorutils._class;

import de.tbd.codegeneratorutils._interface.Interface;

import java.util.List;

public interface ClassInterface {

    ClassMethod withoutInterface();

    ClassMethod withInterface(Interface _interface);

    ClassMethod withInterfaces(List<Interface> interfaces);
}
