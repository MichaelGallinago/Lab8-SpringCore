package org.example.modules;

import java.io.File;

public interface FileModule {
    boolean isFileFormatSupported(String path);

    void writeDescription();

    void method1(File path);

    void method2(File path);

    void method3(File path);
}
