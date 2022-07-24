package com.blacksoft.storagemanager;


import com.blacksoft.storagemanager.callback.ProgressCallback;
import com.blacksoft.storagemanager.config.StorageConfig;
import com.blacksoft.storagemanager.utils.FileType;

import java.io.*;

/**
 * @author AbdelWadoud Rasmi
 * <p>
 * The point of this class is to store files in a structured way and
 * return the path of the stored file (better put in an other thread to not put the main thread
 * under pressure)
 **/

public class FileSaver implements StorageConfig, FileType {
    private FileSaver instance;
    private ProgressCallback progressCallback;

    /**
     * Empty constructor, only if you need to show progress
     */
    private FileSaver() {
        instance = this;
    }

    /**
     * Create an instance of FileSaver
     */
    public static final FileSaver get() {
        return new FileSaver();
    }

    /**
     * Set a progressCallback
     *
     * @param progressCallback: a Callback object to return the current progress
     */
    public final FileSaver setProgressCallBack(ProgressCallback progressCallback) {
        this.progressCallback = progressCallback;
        return this;
    }

    /**
     * Copying inputStream bytes into a new File
     *
     * @param filePathAndName: complete file name with its path.
     * @Returns true if it's a successful operation else false
     */
    public final boolean save(String filePathAndName, InputStream inputStream) {
        try {

            FileOutputStream outputStream = new FileOutputStream(filePathAndName);
            //
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            byte[] buffer = new byte[8192]; /* reading by 8kb (perfect buffer size) */
            int numberOfReadBytes = 0;
            int size;
            while ((size = bufferedInputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, size);
                numberOfReadBytes += size;
                if (progressCallback != null) progressCallback.update(numberOfReadBytes);
            }
            outputStream.flush();
            outputStream.close();
            bufferedInputStream.close();
            inputStream.close();
            //
            outputStream = null;
            bufferedInputStream = null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (inputStream != null) inputStream = null;
        }
        return true;
    }

    /**
     * Saves input stream bytes into a named file.
     *
     * @param path:        directory path where you want to store your file.
     * @param fileName:    file name with extension.
     * @param inputStream: the input stream u want to read from
     */

    public final String save(String path, String fileName, InputStream inputStream) {

        if (inputStream == null) return null;

        //creating parent directories if not existing before
        File dataDir = new File(path);
        dataDir.mkdirs();

        String fileCompleteName = dataDir.getPath() + File.separator + fileName;
        if (save(fileCompleteName, inputStream)) return fileCompleteName;
        else return null;

    }

    /**
     * Saves file content bytes a new file
     *
     * @param file:     your file
     * @param path:     new path where you want to store your file
     * @param fileName: file name with extension
     */

    public final String save(String path, String fileName, File file) {

        if (file == null || !file.isFile()) return null;

        /**
         * creating parent directories if not existing before
         */
        File dataDir = new File(path);
        dataDir.mkdirs();

        try {
            String fileCompleteName = dataDir.getPath() + File.separator + fileName;
            if (save(fileCompleteName, new FileInputStream(file)))
                return fileCompleteName;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
