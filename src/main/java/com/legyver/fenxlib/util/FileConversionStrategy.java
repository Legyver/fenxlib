package com.legyver.fenxlib.util;

import java.io.IOException;
import java.util.Map;

/**
 * Converts a model to/from a String
 */
public interface FileConversionStrategy<T extends FileContext, U> {
	U toModel(String contents, T context) throws IOException, IllegalAccessException;
	String fromModel(U model, T context) throws IOException, IllegalAccessException;
}
