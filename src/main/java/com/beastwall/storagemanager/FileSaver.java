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
public class FileSaver {
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
    public FileSaver setProgressCallBack(ProgressCallback progressCallback) {
        this.progressCallback = progressCallback;
        return this;
    }

    /**
     * Copying inputStream bytes into a new File
     *
     * @param outputFile: complete file name with its path.
     */
    public String save(InputStream inputStream, String outputFile) {
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
    public String save(File file, String outputFile) {
        try {
            return save(new FileInputStream(file), outputFile);
        } catch (Exception e) {
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
    public String save(String inputFile, String outputFile) {
        try {
            return save(new FileInputStream(inputFile), outputFile);
        } catch (Exception e) {
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

    public String save(InputStream inputStream, String path, String fileName) {

        if (inputStream == null) return null;

        //creating parent directories if not existing before
        File dataDir = new File(path);
        dataDir.mkdirs();
        //
        String fileCompleteName = dataDir.getPath() + File.separator + fileName;
        return save(inputStream, fileCompleteName);

    }

    /**
     * Saves file content bytes a new file
     *
     * @param file:     your file
     * @param path:     new path where you want to store your file
     * @param fileName: file name with extension
     */
    public String save(File file, String path, String fileName) {

        if (file == null || !file.isFile()) return null;

        /**
         * creating parent directories if not existing before
         */
        File dataDir = new File(path);
        dataDir.mkdirs();

        try {
            String fileCompleteName = dataDir.getPath() + File.separator + fileName;
            return save(new FileInputStream(file), fileCompleteName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Copying inputStream bytes into a new File
     *
     * @param outputFile:    complete file name with its path.
     * @param fileSavedCall: a callback to return result
     */
    public void saveAsync(InputStream inputStream, String outputFile, FileSavedCall fileSavedCall) {
        new Thread(() -> {
            String result = save(inputStream, outputFile);
            fileSavedCall.result(result != null, result);
        }).start();
    }

    /**
     * Copying input File into a new File
     *
     * @param outputFile:    complete file name with its path.
     * @param fileSavedCall: a callback to return result
     */
    public void saveAsync(File file, String outputFile, FileSavedCall fileSavedCall) {
        new Thread(() -> {
            String result = save(file, outputFile);
            fileSavedCall.result(result != null, result);
        }).start();
    }

    /**
     * Copying input File into a new File
     *
     * @param inputFile:     complete input file name with its path.
     * @param outputFile:    complete input file name with its path.
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
     * @param path:          directory path where you want to store your file.
     * @param fileName:      file name with extension.
     * @param inputStream:   the input stream u want to read from
     * @param fileSavedCall: a callback to return result
     */
    public void saveAsync(InputStream inputStream, String path, String fileName, FileSavedCall fileSavedCall) {
        new Thread(() -> {
            String result = save(inputStream, path, fileName);
            fileSavedCall.result(result != null, result);
        }).start();
    }


    /**
     * Saves file content bytes a new file
     *
     * @param file:          your file
     * @param path:          new path where you wa++nt to store your file
     * @param fileName:      file name with extension
     * @param fileSavedCall: a callback to return result
     */
    public void saveAsync(File file, String path, String fileName, FileSavedCall fileSavedCall) {
        new Thread(() -> {
            String result = save(file, path, fileName);
            fileSavedCall.result(result != null, result);
        }).start();
    }


}
