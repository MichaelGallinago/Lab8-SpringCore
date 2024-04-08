package org.example.utilities;

import java.io.File;

public class FileUtilities {
    public static File getFile(String path) {
        return new File("I:\\Work\\JavaLabs\\Lab8-SpringCore\\Files\\" + path);
    }
}
