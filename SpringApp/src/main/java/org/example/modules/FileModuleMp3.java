package org.example.modules;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class FileModuleMp3 implements FileModule {

    @Override
    public boolean isFileFormatSupported(String path) {
        return path.endsWith(".mp3");
    }

    @Override
    public void writeDescription() {
        System.out.println("Функция №1 - вывод названия трека из тегов");
        System.out.println("Функция №2 - вывод длительности в секундах");
        System.out.println("Функция №3 - вывод битрейта трека");
    }

    @Override
    public void method1(File file) {
        try {
            AudioFile audioFile = AudioFileIO.read(file);

            String title = audioFile.getTag().getFirst(FieldKey.TITLE);

            System.out.println("Название трека: " + title);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void method2(File file) {
        try {
            AudioFile audioFile = AudioFileIO.read(file);

            int durationSeconds = audioFile.getAudioHeader().getTrackLength();

            System.out.println("Длительность трека в секундах: " + durationSeconds);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void method3(File file) {
        try {
            AudioFile audioFile = AudioFileIO.read(file);

            String bitRate = audioFile.getAudioHeader().getBitRate();

            System.out.println("Битрейт: " + bitRate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
