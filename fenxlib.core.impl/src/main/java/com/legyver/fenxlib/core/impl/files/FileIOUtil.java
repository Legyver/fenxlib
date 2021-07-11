package com.legyver.fenxlib.core.impl.files;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.impl.config.JsonApplicationConfig;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;

/**
 * Marshall a file to/from [format] to a POJO.
 * Used for persisting and loading an application config file that is backward and forward compatible
 */
public class FileIOUtil {
	private final FileConversionStrategy strategy;

	/**
	 * Construct a FileIOUtil with a file conversion strategy
	 * @param strategy strategy for converting POJOs to String and vise-versa
	 */
	public FileIOUtil(FileConversionStrategy strategy) {
		this.strategy = strategy;
	}

	/**
	 * Construct a FileIOUtil that saves/reads to/from a [format] file
	 * @param instantiator instantiates a new POJO if one does not exist
	 */
	public FileIOUtil(MapDecoratorPojoInstantiator instantiator) {
		this(new DecoratedMapConversionStrategy(instantiator));
	}

	/**
	 * Construct a FileIOUtil that saves/reads to/from a JSON file
	 */
	public FileIOUtil() {
		this(map -> new JsonApplicationConfig(map));
	}

	/**
	 * Read a POJO from file
	 * @param file the file to read
	 * @return the POJO
	 * @throws IOException if there is an error reading the file
	 * @throws CoreException if there is an error marshalling the file content to the POJO
	 */
	public Object readModel(File file) throws IOException, CoreException {
		return readModel(loadFileToString(file));
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
	protected String loadFileToString(File file) throws IOException {
		return file.exists() ? FileUtils.readFileToString(file, StandardCharsets.UTF_8) : null;
	}

	/**
	 * Write UTF-8 string to a file
	 * @param file the file to write to
	 * @param string the string to write
	 * @throws IOException if there is an error during file-write
	 */
	protected void writeFileFromString(File file, String string) throws IOException {
		FileUtils.write(file, string, StandardCharsets.UTF_8);
	}

}
