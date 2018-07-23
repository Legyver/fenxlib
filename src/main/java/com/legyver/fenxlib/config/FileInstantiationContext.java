package com.legyver.fenxlib.config;

import com.legyver.fenxlib.util.FileContext;
import com.legyver.fenxlib.util.MapDecoratorPojoInstantiator;
import java.io.File;

/**
 * Allows for the model instantiation to be set at runtime
 * to support different models for different files
 */
public class FileInstantiationContext<T extends RawMapAware> implements FileContext {
	private final File file;
	private MapDecoratorPojoInstantiator<T> instantiator;
	
	public FileInstantiationContext(File file, MapDecoratorPojoInstantiator<T> instantiator) {
		this.file = file;
		this.instantiator = instantiator;
	}
	
	public FileInstantiationContext(File file) {
		this(file, null);
	}

	@Override
	public File getFile() {
		return file;
	}

	public MapDecoratorPojoInstantiator<T> getInstantiator() {
		return instantiator;
	}

	public void setInstantiator(MapDecoratorPojoInstantiator<T> instantiator) {
		this.instantiator = instantiator;
	}

}
