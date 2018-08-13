package com.legyver.fenxlib.factory.menu.file.proto;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractFileFactoryOptions implements IFileFactoryOptions {

	protected final List<MenuSection> sections;

	public AbstractFileFactoryOptions(MenuSection... menuSections) {
		sections = Arrays.asList(menuSections);
	}

	public AbstractFileFactoryOptions() {
		this(new FileOpenCloseMenuSection(), new FileSaveMenuSection(), new FileExitMenuSection());
	}

	@Override
	public List<MenuSection> getSections() {
		return sections;
	}

}
