package com.legyver.fenxlib.factory.options;

import java.io.File;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DefaultFileOptions implements FileOptions {
	private final StringProperty sourceName = new SimpleStringProperty();
	private File source;

	public String getSourceName() {
		return sourceName.get();
	}

	public File getSource() {
		return source;
	}

	@Override
	public void setSourceName(String sourceName) {
		this.sourceName.setValue(sourceName);
	}

	@Override
	public void setSource(File source) {
		this.source = source;
	}

	@Override
	public StringProperty sourceNameProperty() {
		return sourceName;
	}

}
