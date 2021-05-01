package com.legyver.fenxlib.core.impl.files;

import com.legyver.core.exception.CoreException;

import java.io.IOException;

/**
 * Converts a model to/from a String
 */
public interface FileConversionStrategy<U> {
	U toModel(String contents) throws CoreException;
	String fromModel(U model) throws CoreException;
}
