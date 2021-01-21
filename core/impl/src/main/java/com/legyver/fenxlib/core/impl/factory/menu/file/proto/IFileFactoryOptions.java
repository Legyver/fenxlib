package com.legyver.fenxlib.core.impl.factory.menu.file.proto;

import com.legyver.fenxlib.core.impl.factory.menu.file.CustomFileExtensionMenuFactory;
import com.legyver.fenxlib.core.impl.uimodel.FileOptions;

import java.io.File;
import java.util.List;

public interface IFileFactoryOptions extends MenuSectionOptions {

	void close(Object na);

	CustomFileExtensionMenuFactory getExtensionFactory();

	List<FileOptions> getRecentFiles();

	FileOptions getWorkingFileOptions();

	void open(File file);

	void save(File file);

	List<MenuSection> getSections();

}
