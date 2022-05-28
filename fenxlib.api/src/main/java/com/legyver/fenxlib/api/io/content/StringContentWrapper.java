package com.legyver.fenxlib.api.io.content;

/**
 * Wrapper to provide content as string
 */
public class StringContentWrapper implements OutputContentWrapper<String> {
    private final String content;

    /**
     * Construct a wrapper around string content to be output
     * @param content the string content to output
     */
    public StringContentWrapper(String content) {
        this.content = content;
    }

    @Override
    public String getContent() {
        return content;
    }
}
