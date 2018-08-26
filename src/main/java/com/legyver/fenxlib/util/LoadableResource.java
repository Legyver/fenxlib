package com.legyver.fenxlib.util;

/**
 * For class.getResourceAsStream(path+name, name)
 */
public class LoadableResource {

	private final String path;
	private final String name;

	public LoadableResource(String path, String name) {
		this.path = path;
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public String getName() {
		return name;
	}

}
