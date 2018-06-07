package com.legyver.fenxlib.locator;

public class AbstractNamedContext {

	private String name;

	public AbstractNamedContext(String name) {
		this.name = name;
	}

	public AbstractNamedContext() {
		//decorator constructor
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
