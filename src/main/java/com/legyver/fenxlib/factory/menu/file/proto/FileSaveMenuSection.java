package com.legyver.fenxlib.factory.menu.file.proto;

import com.legyver.fenxlib.factory.menu.IMenuItemFactory;
import com.legyver.fenxlib.factory.menu.file.SaveCurrentFileFactory;
import com.legyver.fenxlib.factory.menu.file.SaveFileDecorator;
import java.util.Arrays;
import java.util.List;

public class FileSaveMenuSection implements MenuSection<IFileFactoryOptions> {

	@Override
	public List<IMenuItemFactory> getFactories(IFileFactoryOptions fileFactoryOptions) {
		IMenuItemFactory[] factories = {
			new SaveCurrentFileFactory("Save", fileFactoryOptions.getWorkingFileOptions(), fileFactoryOptions::save),
			new SaveFileDecorator("Save As", "Select File", fileFactoryOptions.getExtensionFactory(), fileFactoryOptions::save)};
		return Arrays.asList(factories);
	}

}
