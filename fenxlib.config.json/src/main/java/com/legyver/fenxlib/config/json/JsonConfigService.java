package com.legyver.fenxlib.config.json;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.config.ApplicationConfig;
import com.legyver.fenxlib.api.config.ConfigService;
import com.legyver.fenxlib.api.config.section.ConfigPersisted;
import com.legyver.fenxlib.api.config.section.ConfigSection;
import com.legyver.fenxlib.api.io.IOServiceRegistry;
import com.legyver.fenxlib.api.io.content.StringContentWrapper;
import com.legyver.fenxlib.api.logging.LazyLog;
import com.legyver.utils.adaptex.ExceptionToCoreExceptionActionDecorator;
import com.legyver.utils.jackiso.JacksonObjectMapper;
import com.legyver.utils.jsonmigration.adapter.JSONPathInputAdapter;
import com.legyver.utils.ruffles.SetByMethod;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Loads the application config from file.
 * This was moved to a service to make tests more sensible.
 */
@SuppressWarnings("unchecked")
public class JsonConfigService<T extends ApplicationConfig> implements ConfigService<T> {
	private static final LazyLog logger = new LazyLog(JsonConfigService.class);
	private Class<T> applicationConfigType;

	@Override
	public T loadConfig(String appName, String filename) throws CoreException {
		return (T) new ExceptionToCoreExceptionActionDecorator<T>( () -> {
			InputStream temp = IOServiceRegistry.getInstance().loadInputStream(appName, filename, true);
			if (temp == null) {
				logger.warn("Config file not found: " + filename);
			} else {
				try (InputStream inputStream = temp;
					 BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream)) {
					logger.debug("Loading config file: {}", filename);
					JsonConfig jsonConfig = JacksonObjectMapper.INSTANCE.readValue(bufferedInputStream, JsonConfig.class);
					logger.debug("Config file [{}] loaded", filename);
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
				logger.debug("Persisting field: {} {} on class {}", field.getType(), field.getName(), field.getDeclaringClass());
				ConfigSection configSection = null;
				String getterName = "get" + StringUtils.capitalize(field.getName());
				try {
					logger.debug("Attempting to get value with getter: {}", getterName);
					Method publicGetterMethod = config.getClass().getMethod(getterName);
					if (publicGetterMethod.canAccess(config)) {
						configSection = (ConfigSection) publicGetterMethod.invoke(config);
					}
				} catch (NoSuchMethodException|InvocationTargetException exception) {
					logger.debug("{} unsuccessful. Attempting to get value via reflection", getterName);
					configSection = (ConfigSection) FieldUtils.readField(field, config, true);
				}
				if (configSection == null) {
					logger.warn("Field [" + field + "] has no data");
				} else {
					String configSectionAsString = JacksonObjectMapper.INSTANCE.writeValueAsStringWithPrettyPrint(configSection);
					if (logger.isDebugEnabled()) {
						String logMe = JacksonObjectMapper.INSTANCE.writeValueAsString(configSection);
						logger.debug("Config section data: {}", logMe);
					}
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
		if (logger.isDebugEnabled()) {
			String logMe = JacksonObjectMapper.INSTANCE.writeValueAsString(jsonConfig);
			logger.debug("Full config: {}", logMe);
		}
		return IOServiceRegistry.getInstance().save(new StringContentWrapper(json), filename, true);
	}

	private ApplicationConfig adapt(JsonConfigService.JsonConfig jsonConfig) throws CoreException {
		ApplicationConfig config;
		try {
			config = applicationConfigType.getDeclaredConstructor().newInstance();
			for (Field field : FieldUtils.getFieldsWithAnnotation(applicationConfigType, ConfigPersisted.class)) {
				logger.debug("processing field: {} {} on class {}", field.getType(), field.getName(), field.getDeclaringClass());
				ConfigSection configSection = (ConfigSection) field.getType().getDeclaredConstructor().newInstance();
				logger.debug("ConfigSection instantiated: {}", configSection);

				String sectionName = configSection.getSectionName();
				logger.debug("Config section name: {}, version: {}", configSection.getSectionName(), configSection.getVersion());
				JsonConfigService.JsonConfigSection value = jsonConfig.sections.get(sectionName);
				if (value == null) {
					logger.warn("Config section [" + sectionName + "] has no data");
				} else {
					String version = value.getVersion();
					logger.info("Persisted version: " + version);
					Map<String, Object> data = value.config;
					if (logger.isDebugEnabled()) {
						String sectionContent = JacksonObjectMapper.INSTANCE.writeValueAsString(data);
						logger.debug("Section content: {}" ,sectionContent);
					}
					Object configSectionImpl = new JSONPathInputAdapter<>(field.getType()).adapt(version, data);
					logger.debug("Successfully adapted to {}", configSectionImpl);
					new SetByMethod(field).set(config, configSectionImpl);
					logger.debug("Successfully set value [{}] on field: {}", configSectionImpl, field);
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
