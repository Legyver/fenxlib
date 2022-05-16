package com.legyver.fenxlib.config.json.util;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.files.util.BaseFileIOUtil;
import com.legyver.fenxlib.api.files.util.MapDecoratorPojoInstantiator;
import com.legyver.fenxlib.config.json.JsonApplicationConfig;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;

/**
 * Marshall a file to/from [format] to a POJO.
 * Used for persisting and loading an application config file that is backward and forward compatible
 */
public class JsonFileIOUtil extends BaseFileIOUtil {

	/**
	 * Construct a FileIOUtil that saves/reads to/from a [format] file
	 * @param instantiator instantiates a new POJO if one does not exist
	 */
	public JsonFileIOUtil(MapDecoratorPojoInstantiator instantiator) {
		super(new DecoratedMapConversionStrategy(instantiator));
	}

	/**
	 * Construct a FileIOUtil that saves/reads to/from a JSON file
	 */
	public JsonFileIOUtil() {
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
	 * Read a file content as UTF-8 string
	 * @param file the file
	 * @return the content as a string
	 * @throws IOException if there is an error reading the file
	 */
	@Override
	protected String loadFileToString(File file) throws IOException {
		return file.exists() ? FileUtils.readFileToString(file, StandardCharsets.UTF_8) : null;
	}

	/**
	 * Write UTF-8 string to a file
	 * @param file the file to write to
	 * @param string the string to write
	 * @throws IOException if there is an error during file-write
	 */
	@Override
	protected void writeFileFromString(File file, String string) throws IOException {
		FileUtils.write(file, string, StandardCharsets.UTF_8);
	}

}
