package com.legyver.fenxlib.core.files;

import java.io.IOException;

/**
 * Converts a model to/from a String
 */
public interface FileConversionStrategy<U> {
	U toModel(String contents) throws IOException, IllegalAccessException;
	String fromModel(U model) throws IOException, IllegalAccessException;
}
