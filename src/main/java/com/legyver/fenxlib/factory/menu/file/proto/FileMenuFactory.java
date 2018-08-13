package com.legyver.fenxlib.factory.menu.file.proto;

import com.legyver.fenxlib.factory.menu.IMenuItemFactory;
import com.legyver.fenxlib.factory.menu.MenuFactory;
import com.legyver.fenxlib.factory.menu.file.SeparatorMenuItemFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileMenuFactory extends MenuFactory {

	public FileMenuFactory(IFileFactoryOptions fileFactoryOptions) throws IOException {
		super("File", getFactories(fileFactoryOptions));
	}

	private static IMenuItemFactory[] getFactories(IFileFactoryOptions fileFactoryOptions) throws IOException {
		List<IMenuItemFactory> factories = new ArrayList<>();
		List<MenuSection> sections = fileFactoryOptions.getSections();
		for (int i = 0; i < sections.size(); i++) {
			MenuSection section = sections.get(i);
			factories.addAll(section.getFactories(fileFactoryOptions));
			if (i < sections.size() - 1) {
				factories.add(new SeparatorMenuItemFactory());
			}
		}

		return factories.toArray(new IMenuItemFactory[factories.size()]);
	}

}
