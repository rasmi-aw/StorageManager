package com.blacksoft.storagemanager.callback;

/**
 * @author AbdelWadoud Rasmi
 * <p>
 * shows progress
 */
public abstract class ProgressCallback {
    private int totalBytes;

    /**
     * Constructor
     *
     * @param totalBytes: total bytes to read.
     */
    public ProgressCallback(int totalBytes) {
        this.totalBytes = totalBytes;
    }

    /**
     * calculates progress
     *
     * @param numberOfReadBytes: the number of read bytes
     */
    public void update(int numberOfReadBytes) {
        progress(totalBytes, numberOfReadBytes, totalBytes > 0 ? ((numberOfReadBytes / totalBytes) * 100) : 50);
    }

    /**
     * Shows progress of the file saving operation.
     * This is not an abstract method, in case you don't want to know the progress.
     *
     * @param numberOfReadBytes: number of bytes read till now.
     * @param totalBytes:        total bytes to read.
     * @param percentage:        percentage of read bytes compared to the total number of bytes.
     */
    public abstract void progress(int totalBytes, int numberOfReadBytes, int percentage);
}
