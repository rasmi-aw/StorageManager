package com.beastwall.storagemanager;


import com.beastwall.storagemanager.callback.ProgressCallback;
import com.beastwall.storagemanager.callback.FileSavedCall;
import com.beastwall.storagemanager.utils.StorageUtils;

import java.io.*;

/**
 * @author AbdelWadoud Rasmi
 * <p>
 * The point of this class is to store files in a structured way and
 * return the path of the stored file (better put in an other thread to not put the main thread
 * under pressure)
 **/
public final class FileSaver {
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
     * @param outputFile: complete file name with its path.
     */
    public final String save(String outputFile, InputStream inputStream) {
        String parentDirsPath = null;
        try {
            //Creating parent dirs
            parentDirsPath = StorageUtils.guessFileType(outputFile).toString();
            File parentDirs = new File(parentDirsPath);
            parentDirs.mkdirs();
            //
            FileOutputStream outputStream = new FileOutputStream(parentDirsPath + outputFile);
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
            return null;
        } finally {
            if (inputStream != null) inputStream = null;
        }
        return parentDirsPath + outputFile;
    }

    /**
     * Copying input File into a new File
     *
     * @param outputFile: complete file name with its path.
     */
    public final String save(String outputFile, File file) {
        try {
            return save(outputFile, new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Copying input File into a new File
     *
     * @param inputFile:  complete input file name with its path.
     * @param outputFile: complete input file name with its path.
     */
    public final String save(String inputFile, String outputFile) {
        try {
            return save(outputFile, new FileInputStream(inputFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
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
        //
        String fileCompleteName = dataDir.getPath() + File.separator + fileName;
        return save(fileCompleteName, inputStream);

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
            return save(fileCompleteName, new FileInputStream(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Copying inputStream bytes into a new File
     *
     * @param outputFile:     complete file name with its path.
     * @param fileSavedCall: a callback to return result
     */
    public void saveAsync(String outputFile, InputStream inputStream, FileSavedCall fileSavedCall) {
        new Thread(() -> {
            String result = save(outputFile, inputStream);
            fileSavedCall.result(result != null, result);
        }).start();
    }

    /**
     * Copying input File into a new File
     *
     * @param outputFile:     complete file name with its path.
     * @param fileSavedCall: a callback to return result
     */
    public void saveAsync(String outputFile, File file, FileSavedCall fileSavedCall) {
        new Thread(() -> {
            String result = save(outputFile, file);
            fileSavedCall.result(result != null, result);
        }).start();
    }

    /**
     * Copying input File into a new File
     *
     * @param inputFile:      complete input file name with its path.
     * @param outputFile:     complete input file name with its path.
     * @param fileSavedCall: a callback to return result
     */
    public void saveAsync(String inputFile, String outputFile, FileSavedCall fileSavedCall) {
        new Thread(() -> {
            String result = save(inputFile, outputFile);
            fileSavedCall.result(result != null, result);
        }).start();
    }

    /**
     * Saves input stream bytes into a named file.
     *
     * @param path:           directory path where you want to store your file.
     * @param fileName:       file name with extension.
     * @param inputStream:    the input stream u want to read from
     * @param fileSavedCall: a callback to return result
     */
    public void saveAsync(String path, String fileName, InputStream inputStream, FileSavedCall fileSavedCall) {
        new Thread(() -> {
            String result = save(path, fileName, inputStream);
            fileSavedCall.result(result != null, result);
        }).start();
    }

    /**
     * Saves file content bytes a new file
     *
     * @param file:           your file
     * @param path:           new path where you want to store your file
     * @param fileName:       file name with extension
     * @param fileSavedCall: a callback to return result
     */
    public void saveAsync(String path, String fileName, File file, FileSavedCall fileSavedCall) {
        new Thread(() -> {
            String result = save(path, fileName, file);
            fileSavedCall.result(result != null, result);
        }).start();
    }


}
