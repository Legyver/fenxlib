package com.legyver.fenxlib.api.io.content;

/**
 * Wrapper for byte[] content
 */
public class ByteArrayContentWrapper implements OutputContentWrapper<byte[]> {
    private final byte[] content;

    /**
     * Wrap the specified byte[] content
     * @param content the content
     */
    public ByteArrayContentWrapper(byte[] content) {
        this.content = content;
    }

    @Override
    public byte[] getContent() {
        return content;
    }
}
