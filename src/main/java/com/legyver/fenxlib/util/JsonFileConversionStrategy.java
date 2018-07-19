package com.legyver.fenxlib.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;

public class JsonFileConversionStrategy<T> implements FileConversionStrategy<JsonFileContext, T> {
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	public T toModel(String contents, JsonFileContext context) throws IOException {
		return objectMapper.readValue(contents, context.getType());
	}

	@Override
	public String fromModel(T model, JsonFileContext context) throws JsonProcessingException {
		return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(model);
	}

}
