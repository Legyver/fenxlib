package com.legyver.fenxlib.api.files.util;

import com.legyver.core.exception.CoreException;

import java.io.File;
import java.io.IOException;

/**
 * Base file handling
 */
public abstract class BaseFileIOUtil {
    private final FileConversionStrategy strategy;

    /**
     * Construct a FileIOUtil with a file conversion strategy
     * @param strategy strategy for converting POJOs to String and vise-versa
     */
    public BaseFileIOUtil(FileConversionStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Read a POJO from a string
     * @param fileContents string to marshall to the POJO
     * @return the POJO
     * @throws CoreException if there is a problem marshalling the string to the POJO
     */
    public Object readModel(String fileContents) throws CoreException {
        return strategy.toModel(fileContents);
    }

    /**
     * Save a POJO to a file
     * @param model the POJO to save to the file
     * @param file the file to save the POJO in
     * @throws IOException if an error is raised writing to the file
     * @throws CoreException if an error is raised marshalling the POJO to String
     */
    public void saveModel(Object model, File file) throws IOException, CoreException {
        writeFileFromString(file, strategy.fromModel(model));
    }

    /**
     * Read a file content as UTF-8 string
     * @param file the file
     * @return the content as a string
     * @throws IOException if there is an error reading the file
     */
    abstract protected String loadFileToString(File file) throws IOException;

    /**
     * Write UTF-8 string to a file
     * @param file the file to write to
     * @param string the string to write
     * @throws IOException if there is an error during file-write
     */
    abstract protected void writeFileFromString(File file, String string) throws IOException;
}
