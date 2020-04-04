package com.legyver.fenxlib.factory.options;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * Group string values Helps when needing to keep varargs separate from other
 * String values
 */
public class StringOptionsGroup extends OptionsGroup<String> {

	public StringOptionsGroup(List<String> options) {
		super(options);
	}

	public StringOptionsGroup(String... options) {
		this(options == null ? Collections.EMPTY_LIST : Arrays.asList(options));
	}

}
