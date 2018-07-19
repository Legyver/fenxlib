package com.legyver.fenxlib.util;

import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import com.legyver.fenxlib.util.ForwardBackwardReconcileCache.ReconciliationCtx;
import com.legyver.fenxlib.util.GsonFileContext.ModelInstantiator;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.lang3.reflect.FieldUtils;

public class GsonFileConversionStrategy implements FileConversionStrategy<GsonFileContext, ForwardCompatibleModel> {
	@Override
	public ForwardCompatibleModel toModel(String contents, GsonFileContext context) throws IOException, IllegalAccessException {
		Type mapType = context.getType();
		LinkedTreeMap<String, Map> map = new Gson().fromJson(contents, mapType);
		ModelInstantiator instantiator = context.getModelInstantiator();
		ForwardCompatibleModel workingModel = instantiator.newInstance();
		ForwardCompatibleModel cachedReferenceModel = instantiator.newInstance();
		
		mapToModel(map, workingModel, cachedReferenceModel);//see if uuid already set for item
		ensureUuid(workingModel, cachedReferenceModel);
		ForwardBackwardReconcileCache.cache(map, cachedReferenceModel);
			
		return workingModel;
	}
	
	private void ensureUuid(ForwardCompatibleModel...models) {
		if (models != null) {
			String uuid = models[0].getUuid();
			if (uuid == null) {
				uuid = UUID.randomUUID().toString();
				for (ForwardCompatibleModel model : models) {
					model.setUuid(uuid);
				}
			}
		}
	}

	@Override
	public String fromModel(ForwardCompatibleModel model, GsonFileContext context) throws IOException, IllegalAccessException {
		ensureUuid(model);
		ReconciliationCtx ctx = ForwardBackwardReconcileCache.retrieve(model.getUuid());
		Map<String, Map> map = reconcile(model, ctx);
		return new GsonBuilder().setPrettyPrinting().create().toJson(map, context.getType());
	}

	private void mapToModel(Map<String, Map> map, Object working, Object reference) throws IllegalAccessException {
		Class modelClass = working.getClass();
		List<Field> fields = FieldUtils.getAllFieldsList(modelClass);
		for (Field field : fields) {
			if (map.containsKey(field.getName())) {
				Object value = map.get(field.getName());
				if (value instanceof Map) {
					mapToModel((Map<String, Map>) value, FieldUtils.readField(working, field.getName(), true), FieldUtils.readField(reference, field.getName(), true));
				} else if (field.getType().isPrimitive() || field.getType().equals(String.class)) {
					FieldUtils.writeField(field, working, value, true);
					FieldUtils.writeField(field, reference, value, true);
				}
			}
		}
	}

	private Map<String, Map> reconcile(ForwardCompatibleModel model, ReconciliationCtx ctx) throws IllegalAccessException {
		Map<String, Map> values = ctx.getOriginalMap();
		ForwardCompatibleModel reference = ctx.getReferenceModel();
		reconcile(values, model, reference);
		return values;
	}
	
	private void reconcile(Map values, Object model, Object reference) throws IllegalAccessException {
		Class modelClass = model.getClass();
		List<Field> fields = FieldUtils.getAllFieldsList(modelClass);
		for (Field field : fields) {
			Object value = FieldUtils.readField(model, field.getName(), true);
			Object referenceValue = FieldUtils.readField(reference, field.getName(), true);
			if (value == null) {
				if (referenceValue != null) {//value set null
					values.put(field.getName(), null);
				}
			} else if (field.getType().isPrimitive() || field.getType().equals(String.class)) { 
				if (!value.equals(referenceValue)) {
					values.put(field.getName(), value);
				}
			} else {
				Map fieldMap = (Map) values.get(field.getName());
				if (fieldMap == null) {
					fieldMap = new LinkedHashMap<>();
					values.put(field.getName(), fieldMap);
				}
				reconcile(fieldMap, value, referenceValue); 
			}
		}
	}
	

}
