package com.legyver.fenxlib.widget.about;

import com.legyver.core.exception.CoreException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AboutPageOptions {
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

	public String getTitle() {
		return title;
	}

	public String getIntro() {
		return intro;
	}

	public String getGist() {
		return gist;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public Properties getLicenseProperties() {
		return licenseProperties;
	}

	public Properties getBuildProperties() {
		return buildProperties;
	}

	public Properties getCopyrightProperties() {
		return copyrightProperties;
	}

	public static class Builder {
		private String title;
		private String intro;
		private String gist;
		private String additionalInfo;
		private final Class klass;

		private final Properties licenseProperties = new Properties();
		private final Properties buildProperties = new Properties();
		private final Properties copyrightProperties = new Properties();

		public Builder(Class klass) {
			this.klass = klass;
		}

		public AboutPageOptions build() {
			return new AboutPageOptions(title, intro, gist, additionalInfo, licenseProperties, buildProperties, copyrightProperties);
		}

		public Builder dependenciesFile(String filename) throws CoreException {
			loadProperties(licenseProperties, filename);
			return this;
		}

		public Builder buildPropertiesFile(String filename) throws CoreException {
			loadProperties(buildProperties, filename);
			return this;
		}

		public Builder copyrightPropertiesFile(String filename) throws CoreException {
			loadProperties(copyrightProperties, filename);
			return this;
		}

		private void loadProperties(Properties properties, String filename) throws CoreException {
			try {
				InputStream is = klass.getClassLoader().getResourceAsStream(filename);
				if (is != null) {
					properties.load(is);
				} else {
					throw new CoreException("Unable to find resource: " + filename);
				}
			} catch (IOException ex) {
				throw new CoreException("Unable to load resource: " + filename, ex);
			}
		}

		public Builder title(String title) {
			this.title = title;
			return this;
		}

		public Builder intro(String intro) {
			this.intro = intro;
			return this;
		}

		public Builder gist(String gist) {
			this.gist = gist;
			return this;
		}

		public Builder additionalInfo(String additionalInfo) {
			this.additionalInfo = additionalInfo;
			return this;
		}
	}

}
