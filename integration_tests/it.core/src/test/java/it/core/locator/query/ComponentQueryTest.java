package it.core.locator.query;

import com.legyver.fenxlib.api.locator.DefaultLocationContext;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.locator.LocationContextDecorator;
import com.legyver.fenxlib.api.locator.query.ComponentQuery;
import com.legyver.fenxlib.core.layout.factory.ApplicationBorderPaneLayoutFactory;
import com.legyver.fenxlib.core.layout.factory.StackPaneRegionFactory;
import com.legyver.fenxlib.core.layout.factory.TopRegionFactory;
import com.legyver.fenxlib.core.layout.options.*;
import com.legyver.fenxlib.core.menu.factory.MenuFactory;
import com.legyver.fenxlib.core.scene.controls.factory.ListViewFactory;
import com.legyver.fenxlib.core.scene.controls.factory.TextFieldFactory;
import com.legyver.fenxlib.tests.base.FenxlibTest;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


public class ComponentQueryTest extends FenxlibTest {

	@Test
	public void findOnlyMenuBarComponent() throws Exception {
		BorderPaneInitializationOptions options = new BorderPaneInitializationOptions.Builder()
				.top(new RegionInitializationOptions.Builder()
					.displayContentByDefault()
					.factory(new TopRegionFactory(
						new LeftMenuOptions(
								new MenuFactory("File")
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
		new ApplicationBorderPaneLayoutFactory(options).makeBorderPaneWithContent();

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
