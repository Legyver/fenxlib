package com.legyver.fenxlib.widgets.about;

import com.legyver.fenxlib.api.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.api.controls.options.StyleableControlOptions;
import com.legyver.fenxlib.widgets.license.service.LicenseServiceRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Options for specifying an AboutPage
 */
public class AboutPageOptions extends BaseControlBuilder<AboutPageOptions> implements StyleableControlOptions<AboutPage> {

	private static final Logger logger = LogManager.getLogger(AboutPageOptions.class);
	private final String title;
	private final String intro;
	private final String gist;
	private final String additionalInfo;
	private final Properties licenseProperties;
	private final Properties buildProperties;
	private final Properties copyrightProperties;

	private AboutPageOptions(String title, String intro, String gist, String additionalInfo, Properties licenseProperties, Properties buildProperties, Properties copyrightProperties) {
		this.title = title;
		this.intro = intro;
		this.gist = gist;
		this.additionalInfo = additionalInfo;
		this.licenseProperties = licenseProperties;
		this.buildProperties = buildProperties;
		this.copyrightProperties = copyrightProperties;
	}

	/**
	 * Get the title of the menu option that launches the about page
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Get the first paragraph
	 * @return the intro
	 */
	public String getIntro() {
		return intro;
	}

	/**
	 * Get the second paragraph
	 * @return the gist
	 */
	public String getGist() {
		return gist;
	}

	/**
	 * Get the third paragraph
	 * @return any additional information
	 */
	public String getAdditionalInfo() {
		return additionalInfo;
	}

	/**
	 * Get the license property file
	 * @return the license properties file
	 */
	public Properties getLicenseProperties() {
		return licenseProperties;
	}

	/**
	 * Get the application build properties file
	 * @return the build properties file
	 */
	public Properties getBuildProperties() {
		return buildProperties;
	}

	/**
	 * Get the application copyright properties file
	 * @return the copyright properties file
	 */
	public Properties getCopyrightProperties() {
		return copyrightProperties;
	}

	/**
	 * Builder for specifying AboutPage options
	 */
	public static class Builder {
		private String title;
		private String intro;
		private String gist;
		private String additionalInfo;
		private final Class klass;

		private final Properties licenseProperties = new Properties();
		private final Properties buildProperties = new Properties();
		private final Properties copyrightProperties = new Properties();

		/**
		 * Create a builder to specify about page options
		 * @param klass a class of the application that can be used to load resource files
		 */
		public Builder(Class klass) {
			this.klass = klass;
		}

		/**
		 * Build the about page options
		 * @return the about page options
		 */
		public AboutPageOptions build() {
			try {
				Properties autoProperties = LicenseServiceRegistry.getInstance().loadLicenseProperties();
				for (Object key : autoProperties.keySet()) {
					licenseProperties.computeIfAbsent(key, x -> autoProperties.getProperty((String) key));
				}
			} catch (IOException ex) {
				logger.error("Error loading license resource", ex);
			}
			return new AboutPageOptions(title, intro, gist, additionalInfo, licenseProperties, buildProperties, copyrightProperties);
		}

		/**
		 * Add a license.properties file to be loaded from the classpath
		 * @param filename name of properties file
		 * @return the builder
		 */
		public Builder dependenciesFile(String filename) {
			loadProperties(licenseProperties, filename);
			return this;
		}

		/**
		 * Add a build.properties file to be loaded from the classpath
		 * @param filename filename of the properties file
		 * @return the builder
		 */
		public Builder buildPropertiesFile(String filename) {
			loadProperties(buildProperties, filename);
			return this;
		}

		/**
		 * Add a copyright.properties file to be loaded from the classpath
		 * @param filename filename of the properties file
		 * @return the builder
		 */
		public Builder copyrightPropertiesFile(String filename) {
			loadProperties(copyrightProperties, filename);
			return this;
		}

		private void loadProperties(Properties properties, String filename) {
			try (InputStream is = klass.getClassLoader().getResourceAsStream(filename)) {
				if (is != null) {
					properties.load(is);
				} else {
					logger.error("Unable to find resource: " + filename);
				}
			} catch (IOException ex) {
				logger.error("Error reading properties file: " + filename, ex);
			}
		}

		/**
		 * The title to use for the about page
		 * @param title the title
		 * @return the builder
		 */
		public Builder title(String title) {
			this.title = title;
			return this;
		}

		/**
		 * Add an initial paragraph to the about page
		 * @param intro the first paragraph
		 * @return the builder
		 */
		public Builder intro(String intro) {
			this.intro = intro;
			return this;
		}

		/**
		 * Add a second paragraph to the about page
		 * @param gist the second paragraph
		 * @return the builder
		 */
		public Builder gist(String gist) {
			this.gist = gist;
			return this;
		}

		/**
		 * Add a third paragraph to the about page
		 * @param additionalInfo the third paragraph
		 * @return the builder
		 */
		public Builder additionalInfo(String additionalInfo) {
			this.additionalInfo = additionalInfo;
			return this;
		}
	}

}
