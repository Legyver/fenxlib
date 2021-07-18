package com.legyver.fenxlib.core.impl.factory;

import com.legyver.fenxlib.core.impl.factory.menu.file.SelectFileMenuFactory;
import javafx.stage.FileChooser.ExtensionFilter;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ExtensionFilterFactoryTest {

	@Test
	public void javaFilter() throws Exception {
		List<ExtensionFilter> extensionFilterList = new SelectFileMenuFactory.ExtensionFilterFactory()
				.typeName("Java")
				.extension("*.java").build();
		assertThat(extensionFilterList.size()).isEqualTo(1);
		ExtensionFilter filter = extensionFilterList.iterator().next();
		assertThat(filter.getDescription()).isEqualTo("Java");
		assertThat(filter.getExtensions().size()).isEqualTo(1);
		String extension = filter.getExtensions().iterator().next();
		assertThat(extension).isEqualTo("*.java");
	}
}
