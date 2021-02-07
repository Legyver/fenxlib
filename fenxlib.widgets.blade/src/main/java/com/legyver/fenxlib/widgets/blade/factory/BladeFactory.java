package com.legyver.fenxlib.widgets.blade.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.api.factory.TitledPaneContentFactory;
import com.legyver.fenxlib.widgets.blade.factory.blade.BladeOption;
import com.legyver.fenxlib.widgets.blade.factory.visitor.CenterItemVisitor;
import com.legyver.fenxlib.widgets.blade.factory.visitor.ConstraintVisitor;
import com.legyver.fenxlib.widgets.blade.factory.visitor.LeftItemVisitor;
import com.legyver.fenxlib.widgets.blade.factory.visitor.RightItemVisitor;
import com.legyver.fenxlib.widgets.blade.MoreField;
import javafx.scene.layout.GridPane;
import com.legyver.fenxlib.core.api.locator.LocationContext;
import com.legyver.fenxlib.core.api.locator.LocationContextDecorator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.VBox;

/**
 * Factory for creating a form in a blade widget.
 */
public class BladeFactory implements TitledPaneContentFactory<VBox> {
	private final BladeOption[] bladeOptions;

	/**
	 * Construct a BladeFactory comprised of the nested BladeOptions
	 * @param bladeOptions the blade options to use when constructing the BladeFactory
	 */
	public BladeFactory(BladeOption... bladeOptions) {
		this.bladeOptions = bladeOptions;
	}

	@Override
	public VBox makeNode(LocationContext locationContext) throws CoreException {
		VBox vbox = new VBox();
		GridPane gp = new GridPane();
		vbox.getChildren().add(gp);
		gp.setGridLinesVisible(false);
		gp.setVgap(10);
		if (bladeOptions != null) {
			BladeContext bladeContext = new BladeContext(gp);
			for (int i = 0; i < bladeOptions.length; i++) {
				BladeOption bladeOption = bladeOptions[i];
				LocationContext decoratedLocation = new LocationContextDecorator(locationContext);
				//TODO: maybe these should be configurable?  The reason I'm not doing it now is that in an ideal world, all options should be natively supported without extension. See TODO: in BladeOption
				bladeOption.accept(new LeftItemVisitor(decoratedLocation, bladeContext), i);
				bladeOption.accept(new CenterItemVisitor(decoratedLocation, bladeContext), i);//decoratedContext.name gets set here
				bladeOption.accept(new RightItemVisitor(decoratedLocation, bladeContext), i);
				bladeOption.accept(new ConstraintVisitor(decoratedLocation, bladeContext), i);
			}
			
			MoreField moreTrigger = bladeContext.getMoreTrigger();
			GridPane moreGrid = bladeContext.getMoreGrid();
			if (moreTrigger != null) {
				moreTrigger.expandedProperty().addListener(new ChangeListener<Boolean>(){
					@Override
					public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
						if (newValue) {
							vbox.getChildren().add(moreGrid);
						} else {
							vbox.getChildren().remove(moreGrid);
						}
					}
				});
			}
			
		}
		return vbox;
	}


}
