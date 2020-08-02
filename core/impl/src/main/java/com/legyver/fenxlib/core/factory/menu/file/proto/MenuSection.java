package com.legyver.fenxlib.core.factory.menu.file.proto;

import com.legyver.fenxlib.core.factory.menu.IMenuItemFactory;
import java.util.List;

public interface MenuSection<T extends MenuSectionOptions> {

	List<? extends IMenuItemFactory> getFactories(T menuSectionOptions);
}
