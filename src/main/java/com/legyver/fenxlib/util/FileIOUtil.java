package com.legyver.fenxlib.util;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import org.apache.commons.io.FileUtils;

public class FileIOUtil {
	private FileConversionStrategy strategy = new JsonFileConversionStrategy();

	public void setStrategy(FileConversionStrategy strategy) {
		this.strategy = strategy;
	}
	
	public Object readModel(FileContext context) throws IOException, IllegalAccessException {
		return strategy.toModel(loadFileToString(context.getFile()), context);
	}
	
	public void saveModel(Object model, FileContext context) throws IOException, IllegalAccessException {
		writeFileFromString(context.getFile(), strategy.fromModel(model, context));
	}
	
	private String loadFileToString(File file) throws IOException {
		return FileUtils.readFileToString(file, "UTF-8");
	}
	
	private void writeFileFromString(File file, String string) throws IOException {
		FileUtils.write(file, string, "UTF-8");
	}

}
