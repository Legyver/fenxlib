package org.fenxlib.locator.query;

import com.legyver.fenxlib.locator.query.ComponentQuery;
import com.legyver.fenxlib.locator.query.DefaultComponentRegistry;
import java.util.Optional;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import com.legyver.fenxlib.factory.AccordionMenuFactory;
import com.legyver.fenxlib.factory.BladeFactory;
import com.legyver.fenxlib.factory.BorderPaneFactory;
import com.legyver.fenxlib.factory.TextFieldFactory;
import com.legyver.fenxlib.factory.TitledPaneFactory;
import com.legyver.fenxlib.factory.TopRegionFactory;
import com.legyver.fenxlib.factory.menu.CenterOptions;
import com.legyver.fenxlib.factory.menu.LeftMenuOptions;
import com.legyver.fenxlib.factory.menu.MenuFactory;
import com.legyver.fenxlib.factory.menu.OpenZipFileMenuFactory;
import com.legyver.fenxlib.factory.menu.PopupMenuItemFactory;
import com.legyver.fenxlib.factory.menu.RightMenuOptions;
import com.legyver.fenxlib.factory.options.BorderPaneInitializationOptions;
import com.legyver.fenxlib.factory.options.NameListClickOption;
import com.legyver.fenxlib.util.DefaultApplicationOptions;
import com.legyver.fenxlib.util.GuiUtil;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertTrue;

public class ComponentQueryTest extends ApplicationTest {

	@Test
	public void findOnlyMenuBarComponent() throws Exception {
		DefaultComponentRegistry defaultComponentRegistry = new DefaultComponentRegistry();
		GuiUtil.init(new DefaultApplicationOptions(null, defaultComponentRegistry, null));

		BorderPaneInitializationOptions options = new BorderPaneInitializationOptions.Builder()
				.top()
				.displayContentByDefault()
				.factory(new TopRegionFactory(
						new LeftMenuOptions(
								new MenuFactory("File",
										new OpenZipFileMenuFactory("Open", "Select File", file -> {//noop
										})
								)
						),
						new CenterOptions(new TextFieldFactory(false)),
						new RightMenuOptions(
								new MenuFactory("Help", new PopupMenuItemFactory("About"))
						))).up()
				.build();
		BorderPane root = new BorderPaneFactory(options).makeBorderPane();
		Optional<Node> node = new ComponentQuery.QueryBuilder(defaultComponentRegistry)
				.inRegion(BorderPaneInitializationOptions.REGION_TOP)
				.only().execute();
		assertTrue(node.isPresent());
		assertTrue(node.get() instanceof TextField);
	}

	@Test
	public void findBladeFieldComponent() throws Exception {
		DefaultComponentRegistry defaultComponentRegistry = new DefaultComponentRegistry();
		GuiUtil.init(new DefaultApplicationOptions(null, defaultComponentRegistry, null));

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
