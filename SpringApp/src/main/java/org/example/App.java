package org.example;

import org.example.modules.FileModule;
import org.example.utilities.FileUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

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
        while (true) {
            System.out.println("Введите имя файла для обработки, или введите 0 для выхода");

            FileModule fileModuleForThisFormat = null;

            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            if (input.equals("0")) {
                return;
            }

            for (FileModule fileModule : fileModules) {
                if (fileModule.isFileFormatSupported(input)) {
                    fileModuleForThisFormat = fileModule;
                    break;
                }
            }

            if (fileModuleForThisFormat == null) {
                System.out.println("Данный формат файла не поддерживается");
                continue;
            }

            System.out.println("Выберите, какую из предложенных функций вы бы хотели использовать:");
            fileModuleForThisFormat.writeDescription();

            System.out.print("Введите номер выбранной функции:");
            File file = FileUtilities.getFile(input);
            switch (scanner.nextLine()) {
                case ("1"):
                    fileModuleForThisFormat.method1(file);
                    break;
                case ("2"):
                    fileModuleForThisFormat.method2(file);
                    break;
                case ("3"):
                    fileModuleForThisFormat.method3(file);
                    break;
                default:
                    System.out.println("Вы не ввели правильный номер функции, попробуйте ещё раз");
                    break;
            }
        }
    }
}
