package com.beastwall.storagemanager.callback;

/**
 * @author AbdelWadoud Rasmi
 * <p>
 * shows progress
 */
public interface ProgressCallback {

    /**
     * Shows progress of the file saving operation.
     * This is not an abstract method, in case you don't want to know the progress.
     *
     * @param numberOfReadBytes: number of bytes read till now.
     * @param totalBytes:        total bytes to read.
     * @param percentage:        percentage of read bytes compared to the total number of bytes.
     */
    public abstract void progress(long totalBytes, long numberOfReadBytes, double percentage);
}
