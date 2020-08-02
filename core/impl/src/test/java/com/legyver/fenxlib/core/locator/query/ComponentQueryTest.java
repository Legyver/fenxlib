package com.legyver.fenxlib.core.locator.query;

import java.util.Optional;

import com.legyver.fenxlib.core.context.ApplicationContext;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import com.legyver.fenxlib.core.factory.BorderPaneFactory;
import com.legyver.fenxlib.core.factory.ListViewFactory;
import com.legyver.fenxlib.core.factory.StackPaneRegionFactory;
import com.legyver.fenxlib.core.factory.TextFieldFactory;
import com.legyver.fenxlib.core.factory.TopRegionFactory;
import com.legyver.fenxlib.core.factory.menu.file.OpenFileDecorator;
import com.legyver.fenxlib.core.factory.menu.CenterOptions;
import com.legyver.fenxlib.core.factory.menu.LeftMenuOptions;
import com.legyver.fenxlib.core.factory.menu.MenuFactory;
import com.legyver.fenxlib.core.factory.menu.file.ZipFileMenuFactory;
import com.legyver.fenxlib.core.factory.menu.RightMenuOptions;
import com.legyver.fenxlib.core.factory.menu.file.DefaultFileBrowseLocation;
import com.legyver.fenxlib.core.factory.options.BorderPaneInitializationOptions;
import com.legyver.fenxlib.core.locator.DefaultLocationContext;
import com.legyver.fenxlib.core.locator.LocationContext;
import com.legyver.fenxlib.core.locator.LocationContextDecorator;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertTrue;

public class ComponentQueryTest extends ApplicationTest {

	@Test
	public void findOnlyMenuBarComponent() throws Exception {
		QueryableComponentRegistry defaultComponentRegistry = ApplicationContext.getComponentRegistry();

		BorderPaneInitializationOptions options = new BorderPaneInitializationOptions.Builder()
				.top()
				.displayContentByDefault()
				.factory(new TopRegionFactory(
						new LeftMenuOptions(
								new MenuFactory("File",
										new OpenFileDecorator("Open", "Select File", new ZipFileMenuFactory(new DefaultFileBrowseLocation()), file -> {//noop
										})
								)
						),
						new CenterOptions(new TextFieldFactory(false)),
						new RightMenuOptions())).up()
				.build();
		BorderPane root = new BorderPaneFactory(options).makeBorderPane();
		Optional<Node> node = new ComponentQuery.QueryBuilder(defaultComponentRegistry)
				.inRegion(BorderPaneInitializationOptions.REGION_TOP)
				.only().execute();
		assertTrue(node.isPresent());
		assertTrue(node.get() instanceof TextField);
	}



	@Test
	public void findByType() throws Exception {
		QueryableComponentRegistry defaultComponentRegistry = ApplicationContext.getComponentRegistry();

		BorderPaneInitializationOptions options = new BorderPaneInitializationOptions.Builder()
				.center().factory(new StackPaneRegionFactory(true, new ListViewFactory(true))).up().build();
		BorderPane root = new BorderPaneFactory(options).makeBorderPane();
		Optional<ListView> node = new ComponentQuery.QueryBuilder(defaultComponentRegistry)
				.inRegion(BorderPaneInitializationOptions.REGION_CENTER)
				.type(ListView.class).execute();
		assertTrue(node.isPresent());
		assertTrue(node.get() instanceof ListView);
	}

	@Test
	public void findByLocationContext() throws Exception {
		LocationContext start = new DefaultLocationContext("start");
		LocationContext decorated = new LocationContextDecorator(start);
		decorated.setName("here");
		QueryableComponentRegistry defaultComponentRegistry = ApplicationContext.getComponentRegistry();

		TextFieldFactory tff = new TextFieldFactory(false);
		TextField tf1 = tff.makeNode(decorated);

		Optional<TextField> node = new ComponentQuery.QueryBuilder(defaultComponentRegistry)
				.fromLocation(decorated)
				.only().execute();
		assertTrue(node.isPresent());
		assertTrue(node.get() == tf1);
	}

}
