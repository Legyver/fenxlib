package com.legyver.fenxlib.util;

import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.lang.reflect.Type;
import java.util.Map;

public class GsonFileContext implements FileContext {
	private final Type type = new TypeToken<Map<String, Map>>(){}.getType();
	private final File file;
	private final ModelInstantiator modelInstantiator;
	
	public GsonFileContext(File file, ModelInstantiator modelInstantiator) {
		this.file = file;
		this.modelInstantiator = modelInstantiator;
	}
	
	public Type getType() {
		return type;
	}

	public ModelInstantiator getModelInstantiator() {
		return modelInstantiator;
	}

	@Override
	public File getFile() {
		return file;
	}
	
	@FunctionalInterface
	public interface ModelInstantiator {
		public ForwardCompatibleModel newInstance();
	}

}
