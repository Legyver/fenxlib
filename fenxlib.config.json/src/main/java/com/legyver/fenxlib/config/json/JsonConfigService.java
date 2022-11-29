package com.legyver.fenxlib.config.json;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.config.ApplicationConfig;
import com.legyver.fenxlib.api.config.ConfigService;
import com.legyver.fenxlib.api.config.section.ConfigPersisted;
import com.legyver.fenxlib.api.config.section.ConfigSection;
import com.legyver.fenxlib.api.io.IOServiceRegistry;
import com.legyver.fenxlib.api.io.content.StringContentWrapper;
import com.legyver.utils.adaptex.ExceptionToCoreExceptionActionDecorator;
import com.legyver.utils.jackiso.JacksonObjectMapper;
import com.legyver.utils.jsonmigration.adapter.JSONPathInputAdapter;
import com.legyver.utils.ruffles.SetByMethod;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.MethodUtils;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Loads the application config from file.
 * This was moved to a service to make tests more sensible.
 */
@SuppressWarnings("unchecked")
public class JsonConfigService<T extends ApplicationConfig> implements ConfigService<T> {

	private Class<T> applicationConfigType;

	@Override
	public T loadConfig(String appName, String filename) throws CoreException {
		return (T) new ExceptionToCoreExceptionActionDecorator<T>( () -> {
			InputStream temp = IOServiceRegistry.getInstance().loadInputStream(appName, filename, true);
			if (temp != null) {
				try (InputStream inputStream = temp;
					 BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream)) {
					JsonConfig jsonConfig = JacksonObjectMapper.INSTANCE.readValue(bufferedInputStream, JsonConfig.class);
					return (T) adapt(jsonConfig);
				}
			}
			return null;
		}).execute();
	}

	@Override
	public boolean saveConfig(String filename, ApplicationConfig config) throws CoreException {
		JsonConfig jsonConfig = new JsonConfig();
		for (Field field : FieldUtils.getFieldsWithAnnotation(applicationConfigType, ConfigPersisted.class)) {
			try {
				ConfigSection configSection;
				String getterName = "get" + StringUtils.capitalize(field.getName());
				try {
					configSection = (ConfigSection) MethodUtils.invokeMethod(config, getterName);
				} catch (NoSuchMethodException|InvocationTargetException exception) {
					configSection = (ConfigSection) FieldUtils.readField(field, config, true);
				}
				if (configSection != null) {
					String configSectionAsString = JacksonObjectMapper.INSTANCE.writeValueAsStringWithPrettyPrint(configSection);
					Map<String, Object> configSectionAsMap = JacksonObjectMapper.INSTANCE.readValue(configSectionAsString, Map.class);

					String configSectionName = configSection.getSectionName();
					String configSectionVersion = configSection.getVersion();
					Map<String, Object> values = new HashMap<>();
					for (String entry : configSectionAsMap.keySet()) {
						if (!"sectionName".equals(entry) && !"version".equals(entry)) {
							values.put(entry, configSectionAsMap.get(entry));
						}
					}
					JsonConfigSection jsonConfigSection = new JsonConfigSection();
					jsonConfigSection.sectionName = configSectionName;
					jsonConfigSection.version = configSectionVersion;
					jsonConfigSection.config = values;

					jsonConfigSection.config.put(configSectionName, configSectionAsMap);
					jsonConfig.sections.put(configSectionName, jsonConfigSection);
				}
			} catch (IllegalAccessException e) {
				throw new CoreException(e);
			}
		}
		String json = JacksonObjectMapper.INSTANCE.writeValueAsStringWithPrettyPrint(jsonConfig);
		return IOServiceRegistry.getInstance().save(new StringContentWrapper(json), filename, true);
	}

	private ApplicationConfig adapt(JsonConfigService.JsonConfig jsonConfig) throws CoreException {
		ApplicationConfig config;
		try {
			config = applicationConfigType.getDeclaredConstructor().newInstance();
			for (Field field : FieldUtils.getFieldsWithAnnotation(applicationConfigType, ConfigPersisted.class)) {
				ConfigSection configSection = (ConfigSection) field.getType().getDeclaredConstructor().newInstance();
				String sectionName = configSection.getSectionName();
				JsonConfigService.JsonConfigSection value = jsonConfig.sections.get(sectionName);
				if (value != null) {
					String version = value.getVersion();
					Map<String, Object> data = value.config;
					Object configSectionImpl = new JSONPathInputAdapter<>(field.getType()).adapt(version, data);
					new SetByMethod(field).set(config, configSectionImpl);
				}
			}
			return config;
		} catch (CoreException coreException) {
			throw coreException;
		} catch (Exception ex) {
			throw new CoreException(ex);
		}
	}

	@Override
	public int order() {
		return 10;
	}

	@Override
	public void init(Class<T> applicationConfigType) {
		this.applicationConfigType = applicationConfigType;
	}

	/**
	 * JSON defined configuration
	 * Example {
	 *     "fenxlib.core": {
	 *         //loaded by fenxlib.core
	 *     },
	 *     "fenxlib.widgets.filetree":" {
	 *         //loaded by fenxlib.widgets.filetree
	 *     },
	 *     "my-application" : {
	 *         //loaded by your application
	 *
	 *     }
	 * }
	 */
	public static class JsonConfig {
		/**
		 * Sections of the configuration
		 */
		public Map<String, JsonConfigSection> sections = new HashMap<>();
	}

	/**
	 * JSON defined configuration section.
	 * Example:
	 * {
	 *     "version": "1.0.0",
	 *     "config": {
	 *   		"lastOpened" : {
	 *     			"lastFile" : "C:\\data\\xml\\courses\\clean"
	 *   		}
	 *     }
	 * }
	 */
	public static class JsonConfigSection implements ConfigSection {
		/**
		 * The name of the section.
		 * By convention this should be your application name or library module
		 */
		public String sectionName;
		/**
		 * Version of section written to the configuration.
		 */
		public String version;
		/**
		 * The configuration map
		 */
		public Map<String, Object> config = new HashMap<>();
		@Override
		public String getVersion() {
			return version;
		}

		@Override
		public String getSectionName() {
			return sectionName;
		}

	}
}
