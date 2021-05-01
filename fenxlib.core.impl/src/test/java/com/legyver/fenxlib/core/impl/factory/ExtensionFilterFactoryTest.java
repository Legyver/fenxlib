package com.legyver.fenxlib.core.impl.factory;

import com.legyver.fenxlib.core.impl.factory.menu.file.SelectFileMenuFactory;
import javafx.stage.FileChooser.ExtensionFilter;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ExtensionFilterFactoryTest {

	@Test
	public void javaFilter() throws Exception {
		List<ExtensionFilter> extensionFilterList = new SelectFileMenuFactory.ExtensionFilterFactory()
				.typeName("Java")
				.extension("*.java").build();
		assertEquals(1, extensionFilterList.size());
		ExtensionFilter filter = extensionFilterList.iterator().next();
		assertEquals("Java", filter.getDescription());
		assertEquals(1, filter.getExtensions().size());
		String extension = filter.getExtensions().iterator().next();
		assertEquals("*.java", extension);
	}
}
