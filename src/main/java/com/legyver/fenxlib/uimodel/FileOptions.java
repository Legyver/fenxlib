package com.legyver.fenxlib.uimodel;

import com.legyver.fenxlib.factory.menu.MenuItemOptions;
import java.io.File;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;

public interface FileOptions extends MenuItemOptions, Comparable<FileOptions>  {

	void setFile(File dir);
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
