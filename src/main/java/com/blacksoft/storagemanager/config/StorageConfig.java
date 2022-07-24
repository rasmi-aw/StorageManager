package com.blacksoft.storagemanager.config;

import java.io.File;

/**
 * @author AbdelWadoud Rasmi
 * <p>
 * This interface holds basic configuration to store files,
 * All paths have to be put here in order to be used to determine
 * where each file has to be stored in, the only thing required from you is to fill
 * the main folder path, and other configs
 * @required: you should let the structure of the folders as it is
 */

public interface StorageConfig {

    /**
     * Storage structured folders
     */

    String MAIN_FOLDER = "blacksoft/storagemanager" + File.separator;
    String IMAGES_FOLDER = MAIN_FOLDER + "images" + File.separator;
    String VIDEOS_FOLDER = MAIN_FOLDER + "videos" + File.separator;
    String AUDIOS_FOLDER = MAIN_FOLDER + "audios" + File.separator;
    //
    String OTHER_FILES_FOLDER = MAIN_FOLDER + "others" + File.separator;
    String TEXT_FILES_FOLDER = OTHER_FILES_FOLDER + "text" + File.separator;
    String MICROSOFT_OFFICE_FOLDER = OTHER_FILES_FOLDER + "office" + File.separator;
    String PDF_FOLDER = OTHER_FILES_FOLDER + "pdf" + File.separator;
    String WEB_FILES_FOLDER = OTHER_FILES_FOLDER + "web" + File.separator;
    String WINDOWS_OS_FILES_FOLDER = OTHER_FILES_FOLDER + "windows_os" + File.separator;
    String SQL_DATABASES_FOLDER = OTHER_FILES_FOLDER + "text" + File.separator;

    /**
     * Other configs
     */
    String LIBRARY_NAME = "Arrowbow";

}
