package com.legyver.fenxlib.controls.api.decorator;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.api.locator.DefaultLocationContext;
import com.legyver.fenxlib.core.locator.LocationContext;
import com.legyver.fenxlib.core.api.locator.query.ComponentQuery;
import com.legyver.fenxlib.core.api.locator.query.QueryableComponentRegistry;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import junitx.util.PrivateAccessor;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


public class RegisterAsDecoratorTest extends ApplicationTest {
	private static final String ROOT = "Root";
	private static final String NAME = "My Field";

	@Test
	public void noIconRegistered() throws Exception {
		QueryableComponentRegistry defaultComponentRegistry = ApplicationContext.getComponentRegistry();
		LocationContext locationContext = new DefaultLocationContext(ROOT);
		NodeFactory<TextField> factory = new MockFactory();
		TextField field = factory.makeNode(locationContext);
		Map registered = (Map) PrivateAccessor.getField(defaultComponentRegistry, "nodes");
		assertThat(registered.size()).isEqualTo(0);
	}

	@Test
	public void iconRegistered() throws Exception {
		QueryableComponentRegistry defaultComponentRegistry = ApplicationContext.getComponentRegistry();
		LocationContext locationContext = new DefaultLocationContext(ROOT);
		NodeFactory<TextField> factory = new RegisterAsDecorator(new MockFactory(), NAME);
		TextField field = factory.makeNode(locationContext);
		Map registered = (Map) PrivateAccessor.getField(defaultComponentRegistry, "nodes");
		assertThat(registered.size()).isEqualTo(1);
		Optional<Node> node = new ComponentQuery.QueryBuilder()
				.inRegion(ROOT)
				.named(NAME).execute();
		assertThat(node.isPresent()).isTrue();

	}

	private static class MockFactory implements com.legyver.fenxlib.core.api.factory.StyleableFactory<TextField> {

		@Override
		public TextField makeNode(LocationContext locationContext) throws CoreException {
			return new TextField();
		}

	}

}
