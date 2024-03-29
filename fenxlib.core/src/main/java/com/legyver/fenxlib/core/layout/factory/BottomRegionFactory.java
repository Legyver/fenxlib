package com.legyver.fenxlib.core.layout.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.context.ApplicationContext;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.locator.LocationContextDecorator;
import com.legyver.fenxlib.core.controls.factory.SpaceableFactory;
import com.legyver.fenxlib.core.layout.options.RegionInitializationOptions;
import javafx.beans.binding.When;
import javafx.beans.property.ObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * Factory to create the bottom region of the main layout page.
 * This has knowledge of the left and right menu panels in the main border pane
 * and provides a mechanism to show/hide the left/right menu panels
 */
public class BottomRegionFactory implements SpaceableFactory, RegionFactory<Region, RegionInitializationOptions.SideAwareRegionInitializationOptions> {

	@Override
	public Region makeRegion(BorderPane borderPane, RegionInitializationOptions.SideAwareRegionInitializationOptions regionInitOptions) throws CoreException {
		return toggleSideMenuControls(borderPane.leftProperty(), borderPane.rightProperty(), regionInitOptions);
	}

	private HBox toggleSideMenuControls(ObjectProperty<Node> leftMenu, ObjectProperty<Node> rightMenu, RegionInitializationOptions.SideAwareRegionInitializationOptions regionInitOptions) throws CoreException {
		LocationContextDecorator decoratedContext = new LocationContextDecorator(regionInitOptions.getLocationContext());
		ToggleButton toggleLeft = toggleMenuControl(leftMenu, regionInitOptions.getLeftOptions(), decoratedContext);
		ToggleButton toggleRight = toggleMenuControl(rightMenu, regionInitOptions.getRightOptions(), decoratedContext);

//		if (centerContentFactories != null) {
//			LocationContext locationContext = new DefaultLocationContext(REGION_BOTTOM);
//
//			HBox nodes = new HBox();
//			nodes.getChildren().add(toggleLeft);
//
////			Deque<AlignedNodeFactory<? extends Node>> rightAligned = new ArrayDeque<>();
////			for (AlignedNodeFactory<? extends Node> nodeFactory : centerContentFactories) {
////				if (nodeFactory.getAlignment() == AlignedNodeFactory.EnqueueAlignment.RIGHT) {
////					rightAligned.push(nodeFactory);
////				} else {
////					Node node = nodeFactory.makeNode(locationContext, new MenuItemOptions());
////					nodes.getChildren().add(node);
////				}
////			}
//			nodes.getChildren().add(getSpacer(nodes));
////			for (AlignedNodeFactory<? extends Node> nodeFactory : centerContentFactories) {
////				Node node = nodeFactory.makeNode(locationContext);
////				nodes.getChildren().add(node);
////			}
//			nodes.getChildren().add(toggleRight);
//			return nodes;
//		} else {
//			return spaceNodes(toggleLeft, toggleRight);
//		}
		return null;
	}

	private ToggleButton toggleMenuControl(ObjectProperty<Node> menu, RegionInitializationOptions.SideOptions sideOptions, LocationContext lc) {
		ToggleButton toggleButton = new ToggleButton(sideOptions.getDisplaySideToggleText());
		lc.setName(sideOptions.getDisplaySideToggleText());
		ApplicationContext.getComponentRegistry().register(lc, toggleButton);
		Node menuContent = menu.get();
		menu.bind(new When(toggleButton.selectedProperty()).then(menuContent).otherwise((Node) null));
		toggleButton.setSelected(sideOptions.isDisplaySideByDefault());
		toggleButton.setId("controls-button");
		return toggleButton;
	}

}
