package com.blacksoft.storagemanager.utils;

/**
 * @author AbdelWadoud Rasmi
 * <p>
 * File possible types
 */
public enum FileType {
    NOT_A_FILE(-1),
    IMAGE(3),
    VIDEO(15),
    AUDIO(321),
    TEXT(577),
    MICROSOFT_EXCEL(722),
    MICROSOFT_WORD(753),
    MICROSOFT_POWERPOINT(789),
    PDF(899),
    HTML(948),
    CSS(970),
    XML(1024),
    WINDOWS_EXECUTABLE(1509),
    WINDOWS_EXTERNAL_LIBRARY(1513),
    SQL_DATABASE(1641),
    ANDROID_APPLICATION(1777),
    JAR(1856),
    OTHERS(Integer.MAX_VALUE);

    /**
     * Constructor and value
     */
    private final int value;

    FileType(int value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return String.valueOf(value);
    }


}