package com.legyver.fenxlib.util;

import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;

public class JsonFileContext implements FileContext {
	private final TypeReference type;
	private final File file;
	
	public JsonFileContext(File file, TypeReference type) {
		this.file = file;
		this.type = type;
	}

	public TypeReference getType() {
		return type;
	}

	@Override
	public File getFile() {
		return file;
	}
	
}
