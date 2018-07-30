package com.legyver.fenxlib.uimodel;

import com.legyver.fenxlib.factory.menu.MenuItemOptions;
import java.io.File;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;

public interface FileOptions extends MenuItemOptions {

	void setFile(File dir);
	File getFile();

	void setNewFile(boolean newFile);
	boolean isNewFile();
	BooleanProperty newFileProperty();

	void setFileName(String name);

	void setFilePath(String absolutePath);

	StringProperty filePathProperty();
	StringProperty fileNameProperty();

}
