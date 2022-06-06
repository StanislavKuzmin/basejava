package com.urase.webapp;

import java.io.File;

public class MainFile {
    private static final String DASH = "-";
    static StringBuilder counter = new StringBuilder(DASH);

    public static void main(String[] args) {
        String filePath = "/basejava";
        printFileName(filePath);
    }

    static void printFileName(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            System.out.println("Папки с введеным именем не существует");
        } else if (!dir.isDirectory()) {
            System.out.println("Переданный файл не является папкой");
        } else {
            printAllFilesFromDirectory(dir);
        }
    }

    private static void printAllFilesFromDirectory(File dir) {
        File[] dirFiles = dir.listFiles();
        if (dirFiles != null) {
            for (File file : dirFiles) {
                if (file.isFile()) {
                    System.out.println(counter + file.getName() + " :file");
                }
                if (file.isDirectory()) {
                    System.out.println(counter + file.getName() + " :directory");
                    counter.append(DASH);
                    printAllFilesFromDirectory(file);
                }
            }
            counter.deleteCharAt(counter.length() - 1);
        } else {
            System.out.println("Данный каталог пуст");
        }
    }
}