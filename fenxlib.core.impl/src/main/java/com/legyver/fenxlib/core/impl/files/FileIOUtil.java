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
	
	public FileIOUtil(FileConversionStrategy strategy) {
		this.strategy = strategy;
	}
	
	public FileIOUtil(MapDecoratorPojoInstantiator instantiator) {
		this(new DecoratedMapConversionStrategy(instantiator));
	}
	
	public FileIOUtil() {
		this(map -> new JsonApplicationConfig(map));
	}

	public Object readModel(File file) throws IOException, CoreException {
		return readModel(loadFileToString(file));
	}

	public Object readModel(String fileContents) throws CoreException {
		return strategy.toModel(fileContents);
	}
	
	public void saveModel(Object model, File file) throws IOException, CoreException {
		writeFileFromString(file, strategy.fromModel(model));
	}
	
	protected String loadFileToString(File file) throws IOException {
		return file.exists() ? FileUtils.readFileToString(file, StandardCharsets.UTF_8) : null;
	}
	
	protected void writeFileFromString(File file, String string) throws IOException {
		FileUtils.write(file, string, StandardCharsets.UTF_8);
	}

}
