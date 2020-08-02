package com.legyver.fenxlib.core.factory;

import com.legyver.fenxlib.core.context.ApplicationContext;
import com.legyver.fenxlib.core.factory.options.RegionInitializationOptions.SideAwareRegionInitializationOptions;
import javafx.beans.binding.When;
import javafx.beans.property.ObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import com.legyver.fenxlib.core.factory.options.RegionInitializationOptions.SideOptions;
import com.legyver.fenxlib.core.locator.LocationContext;
import com.legyver.fenxlib.core.locator.LocationContextDecorator;

public enum BottomRegionFactory implements SpaceableFactory, RegionFactory<Region, SideAwareRegionInitializationOptions> {
	INSTANCE;

	@Override
	public Region makeRegion(BorderPane borderPane, SideAwareRegionInitializationOptions regionInitOptions) {
		return toggleSideMenuControls(borderPane.leftProperty(), borderPane.rightProperty(), regionInitOptions);
	}

	private HBox toggleSideMenuControls(ObjectProperty<Node> leftMenu, ObjectProperty<Node> rightMenu, SideAwareRegionInitializationOptions regionInitOptions) {
		LocationContextDecorator decoratedContext = new LocationContextDecorator(regionInitOptions.getLocationContext());
		ToggleButton toggleOptions = toggleMenuControl(leftMenu, regionInitOptions.getLeftOptions(), decoratedContext);
		ToggleButton toggleHistory = toggleMenuControl(rightMenu, regionInitOptions.getRightOptions(), decoratedContext);
		return spaceNodes(toggleOptions, toggleHistory);
	}

	private ToggleButton toggleMenuControl(ObjectProperty<Node> menu, SideOptions sideOptions, LocationContext lc) {
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
