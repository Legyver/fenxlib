package com.legyver.fenxlib.api.io.content;

import java.io.Serializable;

/**
 * Wrapper for serializable content
 */
public class SerializedContentWrapper implements OutputContentWrapper<Serializable> {
    private final Serializable content;

    /**
     * Construct a wrapper for serializable content
     * @param content the serializable content
     */
    public SerializedContentWrapper(Serializable content) {
        this.content = content;
    }

    @Override
    public Serializable getContent() {
        return content;
    }
}
