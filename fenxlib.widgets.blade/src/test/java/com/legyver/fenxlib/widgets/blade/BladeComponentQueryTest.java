package com.legyver.fenxlib.widgets.blade;

import com.legyver.fenxlib.core.controls.factory.AccordionMenuFactory;
import com.legyver.fenxlib.core.controls.factory.TitledPaneFactory;
import com.legyver.fenxlib.core.layout.factory.ApplicationBorderPaneLayoutFactory;
import com.legyver.fenxlib.core.layout.options.BorderPaneInitializationOptions;
import com.legyver.fenxlib.core.layout.options.RegionInitializationOptions;
import com.legyver.fenxlib.core.locator.query.ComponentQuery;
import com.legyver.fenxlib.widgets.blade.factory.BladeFactory;
import com.legyver.fenxlib.widgets.blade.factory.blade.NameListClickOption;
import javafx.scene.control.ListView;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class BladeComponentQueryTest extends ApplicationTest {

	@Test
	public void findBladeFieldComponent() throws Exception {
		BorderPaneInitializationOptions options = new BorderPaneInitializationOptions.Builder()
				.left(new RegionInitializationOptions.SideBuilder("Controls")
					.displayContentByDefault()
					.factory(new AccordionMenuFactory(
						new TitledPaneFactory<>("Source Options", new BladeFactory(
								new NameListClickOption("Sources", false)
						))))
				)
				.build();
		new ApplicationBorderPaneLayoutFactory(options).makeBorderPaneWithContent();

		Optional node = new ComponentQuery.QueryBuilder()
				.inRegion(BorderPaneInitializationOptions.REGION_LEFT)
				.inSubRegion("Source Options")
				.named("Sources").execute();
		assertThat(node.isPresent()).isEqualTo(true);
		assertThat(node.get()).isInstanceOf(ListView.class);
	}
}
