package org.example.modules;

import com.drew.metadata.exif.ExifSubIFDDirectory;
import org.springframework.stereotype.Component;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

import javax.imageio.ImageIO;

@Component
public class FileModuleImage implements FileModule {
    @Override
    public boolean isFileFormatSupported(String path) {
        return path.endsWith(".jpg") || path.endsWith(".png");
    }

    @Override
    public void writeDescription() {
        System.out.println("Функция №1 - вывод размера изображения");
        System.out.println("Функция №2 - вывод информации exif");
        System.out.println("Функция №3 - вывод цветового пространства");
    }

    @Override
    public void method1(File file) {
        try {
            BufferedImage image = ImageIO.read(file);

            System.out.println("Ширина: " + image.getWidth() + " пикселей");
            System.out.println("Высота: " + image.getHeight() + " пикселей");
        } catch (IOException e) {
            System.err.println("Ошибка при чтении изображения: " + e.getMessage());
        }
    }

    @Override
    public void method2(File file) {
        try {
            Metadata metadata = ImageMetadataReader.readMetadata(file);

            ExifSubIFDDirectory directory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);

            if (directory != null) {
                for (Tag tag : directory.getTags()) {
                    System.out.println(tag.getTagName() + " : " + tag.getDescription());
                }
            } else {
                System.out.println("В данном файле нет EXIF информации");
            }
        } catch (ImageProcessingException | IOException e) {
            System.err.println("Ошибка при чтении изображения: " + e.getMessage());
        }
    }

    @Override
    public void method3(File file) {
        try {
            BufferedImage image = ImageIO.read(file);

            System.out.print("Цветовое пространство: ");

            ColorSpace colorSpace = image.getColorModel().getColorSpace();
            int componentsNumber = colorSpace.getNumComponents();
            for (int i = 0; i < componentsNumber; i++) {
                System.out.print(colorSpace.getName(i) + " ");
            }
            System.out.println();
        } catch (IOException e) {
            System.err.println("Ошибка при чтении изображения: " + e.getMessage());
        }
    }
}
