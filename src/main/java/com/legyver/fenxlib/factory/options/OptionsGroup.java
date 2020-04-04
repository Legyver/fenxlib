package com.legyver.fenxlib.factory.options;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 */
public class OptionsGroup<T> {

	private final List<T> options;

	public OptionsGroup(List<T> options) {
		this.options = options;
	}

	public OptionsGroup(T... options) {
		this(options == null ? Collections.EMPTY_LIST : Arrays.asList(options));
	}

	public List<T> getOptions() {
		return options;
	}
}
