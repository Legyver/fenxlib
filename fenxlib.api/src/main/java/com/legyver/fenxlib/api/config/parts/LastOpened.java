package com.legyver.fenxlib.api.config.parts;

/**
 * Last opened file reference
 */
public class LastOpened {
    private String filePath;

    /**
     * Get the file path
     * @return the file path
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Set the file path
     * @param filePath the file path
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
