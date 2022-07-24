package com.blacksoft.storagemanager.utils;

/**
 * @author AbdelWadoud Rasmi
 * <p>
 * File possible types
 */
public interface FileType {
    short NOT_A_FILE = -1;
    short IMAGE = 3;
    short VIDEO = 15;
    short AUDIO = 321;
    short TEXT = 577;
    short MICROSOFT_EXCEL = 722;
    short MICROSOFT_WORD = 753;
    short MICROSOFT_POWERPOINT = 789;
    short PDF = 899;
    short HTML = 948;
    short CSS = 970;
    short XML = 1024;
    short WINDOWS_EXECUTABLE = 1509;
    short WINDOWS_EXTERNAL_LIBRARY = 1513;
    short SQL_DATABASE = 1641;
    short ANDROID_APPLICATION = 1777;
    short JAR = 1856;
    short OTHERS = Short.MAX_VALUE;

}