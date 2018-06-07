package com.legyver.fenxlib.factory.options;

import com.legyver.fenxlib.factory.menu.MenuItemOptions;
import java.io.File;
import javafx.beans.property.StringProperty;

public interface FileOptions extends MenuItemOptions {

	public void setSource(File dir);

	public void setSourceName(String absolutePath);

	public StringProperty sourceNameProperty();
}
