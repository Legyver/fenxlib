package com.legyver.fenxlib.api.io.content;

/**
 * Wrapper to provide variety of saving types.
 * For example:
 *   {@link StringContentWrapper} when the content is a String for saving as text formats (ie: JSON, XML, txt, etc)
 *   {@link SerializedContentWrapper} when the content will just be saved as a serialized format
 *   {@link ByteArrayContentWrapper} when the content is small and just saved as a byte array.
 *
 * @param <T> the type of the content
 */
public interface OutputContentWrapper<T> {
    /**
     * Get the content as the specified type
     * @return the content to output
     */
    T getContent();
}
