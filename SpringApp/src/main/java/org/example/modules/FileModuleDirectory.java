package org.example.modules;

import org.springframework.stereotype.Component;

import java.io.File;

import org.example.utilities.FileUtilities;

@Component
public class FileModuleDirectory implements FileModule {
    @Override
    public boolean isFileFormatSupported(String path) {
        return FileUtilities.getFile(path).isDirectory();
    }

    @Override
    public void writeDescription() {
        System.out.println("Функция №1 - вывод списка файлов в каталоге");
        System.out.println("Функция №2 - подсчет размера всех файлов в каталоге");
        System.out.println("Функция №3 - вывод списка каталогов в каталоге");
    }

    @Override
    public void method1(File directory) {
        File[] files = directory.listFiles();

        if (files == null) return;

        for (File file : files) {
            if (file.isFile()) {
                System.out.println(file.getName());
            }
        }
    }

    @Override
    public void method2(File directory) {
        File[] files = directory.listFiles();

        long size = 0;

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    size += file.length();
                }
            }
        }
        System.out.printf("%d байт", size);
    }

    @Override
    public void method3(File directory) {
        File[] files = directory.listFiles();

        if (files == null) return;

        for (File file : files) {
            if (file.isDirectory()) {
                System.out.println(file.getName());
            }
        }
    }
}
