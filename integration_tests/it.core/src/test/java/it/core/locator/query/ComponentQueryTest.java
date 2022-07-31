package it.core.locator.query;

import com.legyver.fenxlib.api.locator.DefaultLocationContext;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.locator.LocationContextDecorator;
import com.legyver.fenxlib.api.locator.query.ComponentQuery;
import com.legyver.fenxlib.api.regions.ApplicationRegions;
import com.legyver.fenxlib.api.controls.ControlsFactory;
import com.legyver.fenxlib.core.controls.factory.SceneFactory;
import com.legyver.fenxlib.api.layout.IApplicationLayout;
import com.legyver.fenxlib.core.layout.SinglePaneApplicationLayout;
import com.legyver.fenxlib.core.menu.templates.MenuBuilder;
import com.legyver.fenxlib.core.menu.templates.section.FileExitMenuSection;
import com.legyver.fenxlib.tests.base.FenxlibTest;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


public class ComponentQueryTest extends FenxlibTest {

	@Test
	public void findOnlyMenuInMenuBar() throws Exception {
		SinglePaneApplicationLayout layout = new SinglePaneApplicationLayout.SinglePaneApplicationLayoutBuilder()
				.menuBar(new MenuBar(new MenuBuilder()
						.name("File")
						.menuSection(new FileExitMenuSection())
						.build()))
				.build();
		SceneFactory sceneFactory = new TestSceneFactory();
		sceneFactory.makeScene(layout);

		Optional<Menu> fileMenu = new ComponentQuery.QueryBuilder()
				.inRegion(ApplicationRegions.MENUS.getName())
				.typed(Menu.class)
				.execute();

		assertThat(fileMenu.isPresent()).isTrue();
		Menu menu = fileMenu.get();
		assertThat(menu.getText()).isEqualTo("File");
	}

	@Test
	public void findByType() throws Exception {
		ListView findMe = ControlsFactory.make(ListView.class, new DefaultLocationContext(SceneFactory.FENXLIB_MAIN_APPLICATION_PANE));

		AnchorPane anchorPane = new AnchorPane();
		anchorPane.getChildren().add(findMe);

		SinglePaneApplicationLayout layout = new SinglePaneApplicationLayout.SinglePaneApplicationLayoutBuilder()
				.pane(anchorPane)
				.build();
		SceneFactory sceneFactory = new TestSceneFactory();
		sceneFactory.makeScene(layout);

		Optional<ListView> node = new ComponentQuery.QueryBuilder()
				.inRegion(SceneFactory.FENXLIB_MAIN_APPLICATION_PANE)
				.typed(ListView.class).execute();
		assertThat(node.isPresent()).isTrue();
		assertThat(node.get()).isInstanceOf(ListView.class);
	}

	@Test
	public void findByLocationContext() throws Exception {
		LocationContext start = new DefaultLocationContext("start");
		LocationContext decorated = new LocationContextDecorator(start);
		decorated.setName("here");

		TextField tf1 = ControlsFactory.make(TextField.class, decorated);

		Optional<TextField> node = new ComponentQuery.QueryBuilder()
				.fromLocation(decorated)
				.build().execute();
		assertThat(node.isPresent()).isTrue();
		assertThat(node.get()).isEqualTo(tf1);
	}

	private class TestSceneFactory extends SceneFactory {
		/**
		 * Construct a factory to create the scene without loading any stylesheets or applying settings to a stage
		 */
		public TestSceneFactory() {
			super(null);
		}

		@Override
		protected void initStage(Scene scene, IApplicationLayout layout) {
			//noop
		}
	}
}
