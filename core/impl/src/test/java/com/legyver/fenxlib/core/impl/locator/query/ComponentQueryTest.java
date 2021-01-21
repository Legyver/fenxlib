package com.legyver.fenxlib.core.impl.locator.query;

import com.legyver.fenxlib.core.api.locator.DefaultLocationContext;
import com.legyver.fenxlib.core.api.locator.LocationContext;
import com.legyver.fenxlib.core.api.locator.LocationContextDecorator;
import com.legyver.fenxlib.core.api.locator.query.ComponentQuery;
import com.legyver.fenxlib.core.impl.factory.*;
import com.legyver.fenxlib.core.impl.factory.menu.CenterOptions;
import com.legyver.fenxlib.core.impl.factory.menu.LeftMenuOptions;
import com.legyver.fenxlib.core.impl.factory.menu.MenuFactory;
import com.legyver.fenxlib.core.impl.factory.menu.RightMenuOptions;
import com.legyver.fenxlib.core.impl.factory.menu.file.DefaultFileBrowseLocation;
import com.legyver.fenxlib.core.impl.factory.menu.file.OpenFileDecorator;
import com.legyver.fenxlib.core.impl.factory.menu.file.ZipFileMenuFactory;
import com.legyver.fenxlib.core.impl.factory.options.BorderPaneInitializationOptions;
import com.legyver.fenxlib.core.impl.factory.options.RegionInitializationOptions;
import com.legyver.fenxlib.impl.factory.*;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.util.Optional;

import static org.junit.Assert.assertTrue;

public class ComponentQueryTest extends ApplicationTest {

	@Test
	public void findOnlyMenuBarComponent() throws Exception {
		BorderPaneInitializationOptions options = new BorderPaneInitializationOptions.Builder()
				.top(new RegionInitializationOptions.Builder()
					.displayContentByDefault()
					.factory(new TopRegionFactory(
						new LeftMenuOptions(
								new MenuFactory("File",
										new OpenFileDecorator("Open", "Select File", new ZipFileMenuFactory(new DefaultFileBrowseLocation()), file -> {//noop
										})
								)
						),
						new CenterOptions(new TextFieldFactory(false)),
						new RightMenuOptions()))
				)
				.build();
		new BorderPaneFactory(options).makeBorderPane();

		Optional<Node> node = new ComponentQuery.QueryBuilder()
				.inRegion(BorderPaneInitializationOptions.REGION_TOP)
				.only().execute();
		assertTrue(node.isPresent());
		assertTrue(node.get() instanceof TextField);
	}



	@Test
	public void findByType() throws Exception {
		BorderPaneInitializationOptions options = new BorderPaneInitializationOptions.Builder()
				.center(new RegionInitializationOptions.Builder()
						.factory(new StackPaneRegionFactory(true, new ListViewFactory(true)))
				).build();
		new BorderPaneFactory(options).makeBorderPane();

		Optional<ListView> node = new ComponentQuery.QueryBuilder()
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

		TextFieldFactory tff = new TextFieldFactory(false);
		TextField tf1 = tff.makeNode(decorated);

		Optional<TextField> node = new ComponentQuery.QueryBuilder()
				.fromLocation(decorated)
				.only().execute();
		assertTrue(node.isPresent());
		assertTrue(node.get() == tf1);
	}

}
