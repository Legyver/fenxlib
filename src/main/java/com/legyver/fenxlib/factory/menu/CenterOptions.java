package com.legyver.fenxlib.factory.menu;

import com.legyver.fenxlib.factory.NodeFactory;

public class CenterOptions {

	private final NodeFactory factory;

	public CenterOptions(NodeFactory hboxFactory) {
		this.factory = hboxFactory;
	}

	public NodeFactory getFactory() {
		return factory;
	}

}
