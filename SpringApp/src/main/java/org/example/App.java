package org.example;

import org.example.modules.FileModule;
import org.example.utilities.FileUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication(scanBasePackages = "org.example")
public class App {
    private static List<FileModule> fileModules;

    @Autowired
    public App(List<FileModule> fileModules) {
        App.fileModules = fileModules;
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);

        System.out.println("Введите имя файла для обработки:");

        FileModule fileModuleForThisFormat = null;

        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();
        for (FileModule fileModule : fileModules) {
            if (fileModule.isFileFormatSupported(fileName)) {
                fileModuleForThisFormat = fileModule;
                break;
            }
        }

        if (fileModuleForThisFormat == null) {
            System.out.println("Данный формат файла не поддерживается");
            return;
        }

        System.out.println("Выберите, какую из предложенных функций вы бы хотели использовать:");
        fileModuleForThisFormat.writeDescription();
        while (true) {
            System.out.print("Введите номер выбранной функции:");
            File file = FileUtilities.getFile(fileName);
            switch (scanner.nextLine()) {
                case ("1"):
                    fileModuleForThisFormat.method1(file);
                    return;
                case ("2"):
                    fileModuleForThisFormat.method2(file);
                    return;
                case ("3"):
                    fileModuleForThisFormat.method3(file);
                    return;
                default:
                    System.out.println("Вы не ввели правильный номер функции, попробуйте ещё раз");
                    break;
            }
        }
    }
}
