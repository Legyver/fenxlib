package com.legyver.fenxlib.util;

import com.legyver.fenxlib.config.GsonApplicationConfig;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

public class FileIOUtil {
	private FileConversionStrategy strategy = new DecoratedMapConversionStrategy(map -> new GsonApplicationConfig(map));

	public void setStrategy(FileConversionStrategy strategy) {
		this.strategy = strategy;
	}
	
	public Object readModel(FileContext context) throws IOException, IllegalAccessException {
		return strategy.toModel(loadFileToString(context.getFile()), context);
	}
	
	public void saveModel(Object model, FileContext context) throws IOException, IllegalAccessException {
		writeFileFromString(context.getFile(), strategy.fromModel(model, context));
	}
	
	protected String loadFileToString(File file) throws IOException {
		return FileUtils.readFileToString(file, "UTF-8");
	}
	
	protected void writeFileFromString(File file, String string) throws IOException {
		FileUtils.write(file, string, "UTF-8");
	}

}
