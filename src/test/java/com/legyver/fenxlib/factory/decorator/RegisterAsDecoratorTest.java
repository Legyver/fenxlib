package com.legyver.fenxlib.factory.decorator;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.factory.NodeFactory;
import com.legyver.fenxlib.locator.DefaultLocationContext;
import com.legyver.fenxlib.locator.LocationContext;
import com.legyver.fenxlib.locator.query.ComponentQuery;
import com.legyver.fenxlib.locator.query.DefaultComponentRegistry;
import com.legyver.fenxlib.util.GuiUtil;
import java.util.Map;
import java.util.Optional;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import junitx.util.PrivateAccessor;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class RegisterAsDecoratorTest extends ApplicationTest {
	private static final String ROOT = "Root";
	private static final String NAME = "My Field";

	@Test
	public void noIconRegistered() throws Exception {
		DefaultComponentRegistry defaultComponentRegistry = GuiUtil.getComponentRegistry();
		LocationContext locationContext = new DefaultLocationContext(ROOT);
		NodeFactory<TextField> factory = new MockFactory();
		TextField field = factory.makeNode(locationContext);
		Map registered = (Map) PrivateAccessor.getField(defaultComponentRegistry, "nodes");
		assertThat(registered.size(), is(0));
	}

	@Test
	public void iconRegistered() throws Exception {
		DefaultComponentRegistry defaultComponentRegistry = GuiUtil.getComponentRegistry();
		LocationContext locationContext = new DefaultLocationContext(ROOT);
		NodeFactory<TextField> factory = new RegisterAsDecorator(new MockFactory(), NAME);
		TextField field = factory.makeNode(locationContext);
		Map registered = (Map) PrivateAccessor.getField(defaultComponentRegistry, "nodes");
		assertThat(registered.size(), is(1));
		Optional<Node> node = new ComponentQuery.QueryBuilder(defaultComponentRegistry)
				.inRegion(ROOT)
				.named(NAME).execute();
		assertTrue(node.isPresent());

	}

	private static class MockFactory implements NodeFactory<TextField> {

		@Override
		public TextField makeNode(LocationContext locationContext) throws CoreException {
			return new TextField();
		}

	}

}
