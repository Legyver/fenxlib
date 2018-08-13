package com.legyver.fenxlib.factory.menu.file.proto;

import com.legyver.fenxlib.factory.menu.IMenuItemFactory;
import java.util.List;

public interface MenuSection<T extends MenuSectionOptions> {

	List<? extends IMenuItemFactory> getFactories(T menuSectionOptions);
}
