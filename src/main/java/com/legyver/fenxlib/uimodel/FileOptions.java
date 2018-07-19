package com.legyver.fenxlib.uimodel;

import com.legyver.fenxlib.factory.menu.MenuItemOptions;
import java.io.File;
import javafx.beans.property.StringProperty;

public interface FileOptions extends MenuItemOptions {

	public void setFile(File dir);
	
	public void setFileName(String name);

	public void setFilePath(String absolutePath);

	public StringProperty filePathProperty();
}
