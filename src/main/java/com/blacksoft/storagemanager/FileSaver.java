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
    private ProgressCallback progressCallback;

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
    public final boolean copy(String filePathAndName, InputStream inputStream) {
        try {

            FileOutputStream outputStream = new FileOutputStream(filePathAndName);
            //
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            byte[] buffer = new byte[8192]; /* reading by 8kb (perfect buffer size) */
            int totalBytes = inputStream.available();
            int numberOfReadBytes = 0;
            int size;
            while ((size = bufferedInputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, size);
                numberOfReadBytes += size;
                int percentage = ((int) Math.round(((((double) numberOfReadBytes) / totalBytes) * 100)));
                if (progressCallback != null) {
                    progressCallback.progress(totalBytes, numberOfReadBytes, totalBytes > 0 ? percentage : 50);
                }
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
     * Copying input File into a new File
     *
     * @param filePathAndName: complete file name with its path.
     * @Returns true if it's a successful operation else false
     */
    public final boolean copy(String filePathAndName, File file) {
        try {
            return copy(filePathAndName, new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Copying input File into a new File
     *
     * @param : complete file name with its path.
     * @Returns true if it's a successful operation else false
     */
    public final boolean copy(String inputFile, String outputFile) {
        try {
            return copy(outputFile, new FileInputStream(inputFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
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
        if (copy(fileCompleteName, inputStream)) return fileCompleteName;
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
            if (copy(fileCompleteName, new FileInputStream(file)))
                return fileCompleteName;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
