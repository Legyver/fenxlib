package com.legyver.fenxlib.api.io.content;

import java.nio.channels.Pipe;

/**
 * Wrapper for a pipe the output content will be written down
 */
public class PipeContentWrapper implements OutputContentWrapper<Pipe> {
    private final Pipe content;
    private final int bufferSize;

    /**
     * Construct a wrapper for a pipe with the specified buffer size
     * @param pipe the pipe to wrap
     * @param bufferSize the size of buffer that will be written
     */
    public PipeContentWrapper(Pipe pipe, int bufferSize) {
        this.content = pipe;
        this.bufferSize = bufferSize;
    }

    /**
     * Construct a wrapper for a pipe with the default buffer size of 1024 bytes
     * @param pipe the pipe to wrap
     */
    public PipeContentWrapper(Pipe pipe) {
        this(pipe, 1024);
    }

    @Override
    public Pipe getContent() {
        return content;
    }

    /**
     * Get the buffer size to use when reading the pipe
     * @return the buffer size
     */
    public int getBufferSize() {
        return bufferSize;
    }

}
