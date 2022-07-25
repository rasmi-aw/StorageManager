package com.blacksoft.storagemanager;

import com.blacksoft.storagemanager.utils.MimeType;
import com.blacksoft.storagemanager.utils.StorageUtils;

import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter File full path: ");
        String path = scanner.nextLine();
        File file = new File(path);
        long time = System.currentTimeMillis();
        FileSaver
                .get()
                .saveAsync(path, StorageUtils.randomNameWithExtension("rasmi", MimeType.IMAGE_PNG), (success, file1) -> {
                    System.out.println(file1);
                });
        System.out.println(System.currentTimeMillis()-time);
    }
}
