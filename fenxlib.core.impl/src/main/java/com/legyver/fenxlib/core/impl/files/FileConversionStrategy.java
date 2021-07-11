package com.legyver.fenxlib.core.impl.files;

import com.legyver.core.exception.CoreException;

import java.io.IOException;

/**
 * Converts a model to/from a String
 */
public interface FileConversionStrategy<U> {
	/**
	 * Convert a String to a POJO
	 * @param contents the serialized content of the POJO
	 * @return the deserialized POJO
	 * @throws CoreException if there is an error during conversion
	 */
	U toModel(String contents) throws CoreException;

	/**
	 * Converts a POJO to a String so it can be saved
	 * @param model the POJO
	 * @return the serialized POJO
	 * @throws CoreException if there is an error during conversion
	 */
	String fromModel(U model) throws CoreException;
}
