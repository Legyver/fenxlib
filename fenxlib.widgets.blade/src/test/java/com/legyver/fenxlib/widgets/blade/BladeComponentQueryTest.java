package com.legyver.fenxlib.widgets.blade;

import com.legyver.fenxlib.core.impl.factory.AccordionMenuFactory;
import com.legyver.fenxlib.core.impl.factory.BorderPaneFactory;
import com.legyver.fenxlib.core.impl.factory.TitledPaneFactory;
import com.legyver.fenxlib.core.impl.factory.options.BorderPaneInitializationOptions;
import com.legyver.fenxlib.core.impl.factory.options.RegionInitializationOptions;
import com.legyver.fenxlib.core.api.locator.query.ComponentQuery;
import com.legyver.fenxlib.widgets.blade.factory.BladeFactory;
import com.legyver.fenxlib.widgets.blade.factory.blade.NameListClickOption;
import javafx.scene.control.ListView;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.util.Optional;

import static org.junit.Assert.assertTrue;

public class BladeComponentQueryTest extends ApplicationTest {

	@Test
	public void findBladeFieldComponent() throws Exception {
		BorderPaneInitializationOptions options = new BorderPaneInitializationOptions.Builder()
				.left(new RegionInitializationOptions.SideBuilder("Controls")
					.displayContentByDefault()
					.factory(new AccordionMenuFactory(
						new TitledPaneFactory("Source Options", new BladeFactory(
								new NameListClickOption("Sources", false)
						))))
				)
				.build();
		new BorderPaneFactory(options).makeBorderPane();

		Optional<ListView> node = new ComponentQuery.QueryBuilder()
				.inRegion(BorderPaneInitializationOptions.REGION_LEFT)
				.inSubRegion("Source Options")
				.named("Sources").execute();
		assertTrue(node.isPresent());
		assertTrue(node.get() instanceof ListView);
	}
}
