package com.legyver.fenxlib.core.impl.locator.query;

import com.legyver.fenxlib.core.api.factory.layout.ApplicationBorderPaneLayoutFactory;
import com.legyver.fenxlib.core.api.locator.DefaultLocationContext;
import com.legyver.fenxlib.core.locator.LocationContext;
import com.legyver.fenxlib.core.api.locator.LocationContextDecorator;
import com.legyver.fenxlib.core.api.locator.query.ComponentQuery;
import com.legyver.fenxlib.core.impl.config.options.TestApplicationOptionsBuilder;
import com.legyver.fenxlib.core.config.options.init.RecentFilesApplicationLifecycleHook;
import com.legyver.fenxlib.core.api.factory.layout.options.CenterOptions;
import com.legyver.fenxlib.core.api.factory.layout.options.LeftMenuOptions;
import com.legyver.fenxlib.core.api.factory.menu.MenuFactory;
import com.legyver.fenxlib.core.api.factory.layout.options.RightMenuOptions;
import com.legyver.fenxlib.core.api.factory.menu.file.OpenFileDecorator;
import com.legyver.fenxlib.core.api.factory.menu.file.SelectFileMenuFactory;
import com.legyver.fenxlib.core.api.factory.layout.options.BorderPaneInitializationOptions;
import com.legyver.fenxlib.core.api.factory.layout.options.RegionInitializationOptions;
import com.legyver.fenxlib.core.impl.util.TestApplicationResource;
import com.legyver.fenxlib.core.impl.util.TestConfig;
import com.legyver.fenxlib.core.impl.util.TestUiModel;
import com.legyver.fenxlib.controls.api.node.ListViewFactory;
import com.legyver.fenxlib.core.api.factory.layout.StackPaneRegionFactory;
import com.legyver.fenxlib.controls.api.node.TextFieldFactory;
import com.legyver.fenxlib.core.api.factory.layout.TopRegionFactory;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.io.File;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


public class ComponentQueryTest extends ApplicationTest {

	@Test
	public void findOnlyMenuBarComponent() throws Exception {
		new TestApplicationOptionsBuilder()
				.appName("Test")
				.customAppConfigProvider(new TestApplicationResource("TestApplicationConfig_lastopened.json"))
				.customAppConfigInstantiator(map -> new TestConfig(map))
				.uiModel(new TestUiModel())
				.customRecentFilesMixin(new RecentFilesApplicationLifecycleHook() {
					@Override
					protected boolean isDirectoryValid(File file) {
						return true;
					}
				})
				.disableLogging()
				.disableAutosaveConfig()
				.build();//build() automatically bootstraps the application

		BorderPaneInitializationOptions options = new BorderPaneInitializationOptions.Builder()
				.top(new RegionInitializationOptions.Builder()
					.displayContentByDefault()
					.factory(new TopRegionFactory(
						new LeftMenuOptions(
								new MenuFactory("File",
										new OpenFileDecorator("Open", "Select File", new SelectFileMenuFactory(Collections.emptyList()), file -> {//noop
										})
								)
						),
						new CenterOptions(new TextFieldFactory(false)),
						new RightMenuOptions()))
				)
				.build();
		new ApplicationBorderPaneLayoutFactory(options).makeBorderPaneWithContent();

		Optional<Node> node = new ComponentQuery.QueryBuilder()
				.inRegion(BorderPaneInitializationOptions.REGION_TOP)
				.only().execute();
		assertThat(node.isPresent()).isTrue();
		assertThat(node.get()).isInstanceOf(TextField.class);
	}



	@Test
	public void findByType() throws Exception {
		BorderPaneInitializationOptions options = new BorderPaneInitializationOptions.Builder()
				.center(new RegionInitializationOptions.Builder()
						.factory(new StackPaneRegionFactory(true, new ListViewFactory(true)))
				).build();
		new BorderPaneFactory(options).makeBorderPaneWithContent();

		Optional<ListView> node = new ComponentQuery.QueryBuilder()
				.inRegion(BorderPaneInitializationOptions.REGION_CENTER)
				.type(ListView.class).execute();
		assertThat(node.isPresent()).isTrue();
		assertThat(node.get()).isInstanceOf(ListView.class);
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
		assertThat(node.isPresent()).isTrue();
		assertThat(node.get()).isEqualTo(tf1);
	}

}
