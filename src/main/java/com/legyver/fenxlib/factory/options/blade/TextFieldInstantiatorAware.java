package com.legyver.fenxlib.factory.options.blade;

import com.jfoenix.controls.JFXTextField;
import com.legyver.fenxlib.factory.NodeInstantiator;

public interface TextFieldInstantiatorAware {

	NodeInstantiator<JFXTextField> getInstantiator();
}
