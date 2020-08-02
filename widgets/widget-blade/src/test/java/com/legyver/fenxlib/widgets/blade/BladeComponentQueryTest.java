package com.legyver.fenxlib.widgets.blade;

import com.legyver.fenxlib.core.context.ApplicationContext;
import com.legyver.fenxlib.core.factory.AccordionMenuFactory;
import com.legyver.fenxlib.core.factory.BorderPaneFactory;
import com.legyver.fenxlib.core.factory.TitledPaneFactory;
import com.legyver.fenxlib.core.factory.options.BorderPaneInitializationOptions;
import com.legyver.fenxlib.core.locator.query.ComponentQuery;
import com.legyver.fenxlib.core.locator.query.QueryableComponentRegistry;
import com.legyver.fenxlib.widgets.blade.factory.BladeFactory;
import com.legyver.fenxlib.widgets.blade.factory.blade.NameListClickOption;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.util.Optional;

import static org.junit.Assert.assertTrue;

public class BladeComponentQueryTest extends ApplicationTest {

	@Test
	public void findBladeFieldComponent() throws Exception {
		QueryableComponentRegistry defaultComponentRegistry = ApplicationContext.getComponentRegistry();

		BorderPaneInitializationOptions options = new BorderPaneInitializationOptions.Builder()
				.left("Controls")
				.displayContentByDefault()
				.factory(new AccordionMenuFactory(
						new TitledPaneFactory("Source Options", new BladeFactory(
								new NameListClickOption("Sources", false)
						)))).up()
				.build();
		BorderPane root = new BorderPaneFactory(options).makeBorderPane();
		Optional<ListView> node = new ComponentQuery.QueryBuilder(defaultComponentRegistry)
				.inRegion(BorderPaneInitializationOptions.REGION_LEFT)
				.inSubRegion("Source Options")
				.named("Sources").execute();
		assertTrue(node.isPresent());
		assertTrue(node.get() instanceof ListView);
	}
}
