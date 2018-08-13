package com.legyver.fenxlib.util;

import com.legyver.fenxlib.config.GsonApplicationConfig;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

public class FileIOUtil {
	private final FileConversionStrategy strategy;
	
	public FileIOUtil(FileConversionStrategy strategy) {
		this.strategy = strategy;
	}
	
	public FileIOUtil(MapDecoratorPojoInstantiator instantiator) {
		this(new DecoratedMapConversionStrategy(instantiator));
	}
	
	public FileIOUtil() {
		this(map -> new GsonApplicationConfig(map));
	}
	
	public Object readModel(File file) throws IOException, IllegalAccessException {
		return strategy.toModel(loadFileToString(file));
	}
	
	public void saveModel(Object model, File file) throws IOException, IllegalAccessException {
		writeFileFromString(file, strategy.fromModel(model));
	}
	
	protected String loadFileToString(File file) throws IOException {
		return FileUtils.readFileToString(file, "UTF-8");
	}
	
	protected void writeFileFromString(File file, String string) throws IOException {
		FileUtils.write(file, string, "UTF-8");
	}

}
