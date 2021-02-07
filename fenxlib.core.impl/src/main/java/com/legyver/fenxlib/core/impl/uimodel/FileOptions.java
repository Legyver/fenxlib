package com.legyver.fenxlib.core.impl.uimodel;

import com.legyver.fenxlib.core.impl.factory.menu.MenuItemOptions;
import java.io.File;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;

public interface FileOptions extends MenuItemOptions, Comparable<FileOptions>  {

	File getFile();

	void setNewFile(boolean newFile);
	boolean isNewFile();
	BooleanProperty newFileProperty();

	void setFileName(String name);
	String getFileName();
	StringProperty fileNameProperty();

	void setFilePath(String absolutePath);
	String getFilePath();
	StringProperty filePathProperty();

}
