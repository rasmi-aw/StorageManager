package com.beastwall.storagemanager.callback;

/**
 * @author AbdelWadoud Rasmi
 * <p>
 * A callback to return result file
 */

public interface FileSavedCall {

    void result(boolean success, String file);
}
