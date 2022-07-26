package com.beastwall.storagemanager.utils;

import java.io.File;

/**
 * @author AbdelWadoud Rasmi
 * <p>
 * File possible types
 */
public enum FileType {
    NOT_A_FILE(StorageUtils.STORAGE_MAIN_FOLDER),
    IMAGE(StorageUtils.STORAGE_MAIN_FOLDER + "image" + File.separator),
    VIDEO(StorageUtils.STORAGE_MAIN_FOLDER + "video" + File.separator),
    AUDIO(StorageUtils.STORAGE_MAIN_FOLDER + "audio" + File.separator),
    TEXT(StorageUtils.STORAGE_MAIN_FOLDER + "text" + File.separator),
    MICROSOFT_EXCEL(StorageUtils.OTHER_FILES_FOLDER + "excel" + File.separator),
    MICROSOFT_WORD(StorageUtils.OTHER_FILES_FOLDER + "word" + File.separator),
    MICROSOFT_POWERPOINT(StorageUtils.STORAGE_MAIN_FOLDER + "powerpoint" + File.separator),
    PDF(StorageUtils.OTHER_FILES_FOLDER + "pdf" + File.separator),
    HTML(StorageUtils.OTHER_FILES_FOLDER + "html" + File.separator),
    CSS(StorageUtils.OTHER_FILES_FOLDER + "css" + File.separator),
    XML(StorageUtils.OTHER_FILES_FOLDER + "xml" + File.separator),
    WINDOWS_EXECUTABLE(StorageUtils.OTHER_FILES_FOLDER + "windows_executable" + File.separator),
    WINDOWS_EXTERNAL_LIBRARY(StorageUtils.OTHER_FILES_FOLDER + "windows_library" + File.separator),
    SQL_DATABASE(StorageUtils.OTHER_FILES_FOLDER + "sql" + File.separator),
    ANDROID_APPLICATION(StorageUtils.OTHER_FILES_FOLDER + "android" + File.separator),
    JAR(StorageUtils.OTHER_FILES_FOLDER + "jar" + File.separator),
    OTHERS(StorageUtils.OTHER_FILES_FOLDER);

    /**
     * Constructor and value
     */
    private final String value;

    FileType(String value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return value;
    }


}