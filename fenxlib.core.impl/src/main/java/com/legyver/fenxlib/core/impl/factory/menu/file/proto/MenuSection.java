package com.legyver.fenxlib.core.impl.factory.menu.file.proto;

import com.legyver.fenxlib.core.impl.factory.menu.IMenuItemFactory;
import java.util.List;

public interface MenuSection<T extends MenuSectionOptions> {

	List<? extends IMenuItemFactory> getFactories(T menuSectionOptions);
}
