package com.legyver.fenxlib.factory.menu.file.proto;

import com.legyver.fenxlib.factory.menu.IMenuItemFactory;
import com.legyver.fenxlib.factory.menu.file.CloseCurrentFileFactory;
import com.legyver.fenxlib.factory.menu.file.OpenFileDecorator;
import com.legyver.fenxlib.factory.menu.file.OpenRecentFilesFactory;
import java.util.Arrays;
import java.util.List;

public class FileOpenCloseMenuSection implements MenuSection<IFileFactoryOptions> {

	@Override
	public List<IMenuItemFactory> getFactories(IFileFactoryOptions fileFactoryOptions) {
		IMenuItemFactory[] factories = {
			new OpenFileDecorator("Open", "Select File", fileFactoryOptions.getExtensionFactory(), fileFactoryOptions::open),
			new OpenRecentFilesFactory(fileFactoryOptions.getRecentFiles(), fileFactoryOptions::open),
			new CloseCurrentFileFactory("Close", fileFactoryOptions.getWorkingFileOptions(), fileFactoryOptions::close),};
		return Arrays.asList(factories);
	}

}
