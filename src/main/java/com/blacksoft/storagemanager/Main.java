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
        FileSaver
                .get()
                .setProgressCallBack((totalBytes, numberOfReadBytes, percentage) -> {})
                .copy(path, StorageUtils.randomNameWithExtension("rasmi", MimeType.IMAGE_BITMAP));

    }
}
