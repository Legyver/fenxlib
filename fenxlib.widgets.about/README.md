# AboutPage
Display an about page with the following customizable elements (listed from top-to-bottom)
- A title for the about page
- A build message specified by a **build.message** property.  See [property transformation rules](#property-transformation-rules) below.
- An introduction paragraph
- Two additional paragraphs
- A hard-coded message ***Powered by open source***
- A list of all open source dependencies, and their licenses read from a union of
  - *licensePropertiesFile()* specified by the developer
  - Any dependency license information read from Legyver's self-reporting modules.
- A copyright message specified by a **copyright** property

## Usage
### Dependency
```gradle
    implementation group: 'com.legyver', name: 'fenxlib.widgets.about', version: '3.0.0-alpha.1'
```
### Coding
```java
AboutPageOptions aboutPageOptions = new AboutPageOptions.Builder(getClass())
    .buildPropertiesFile("build.properties")
    .copyrightPropertiesFile("copyright.properties")
    .title("MyApplication")
    .intro("MyApplication makes amazing things easy")
    .gist("More stuff about how great this app is.  I can go on about it for a really long time and the text will wrap around.")
    .additionalInfo("be sure to tell your friends")
    .build();
```
### Resources
build.properties
```properties
major.version=1
minor.version=0
patch.number=0

build.number=0000
build.date.day=13
build.date.month=February
build.date.year=2021

#Note: Expression must be a valid slel expression.
#below ends up being injected into build.date
build.date.format=${build.date.day} ${build.date.month} ${build.date.year}

#below ends up being injected into build.version
build.version.format=${major.version}.${minor.version}.${patch.number}.${build.number}

#below ends up being injected into build.message displayed on about screen
build.message.format=Build ${build.version}, built on ${build.date}
```
copyright.properties
```properties
#Note: The expression must be a valid slel expression.
#Since both build.properties and copyright.properties are loaded into the same context, you can
#reference properties in the other file as below
copyright.format=Copyright � Legyver 2020-${build.date.year}.
```
### Output
![About Page](https://user-images.githubusercontent.com/3435255/107864578-92c58780-6e2b-11eb-8b87-5beee11504d0.png)

## Property Transformation Rules
Three properties can be constructed by a property graph comprised of the properties defined in the *copyrightPropertiesFile()* and *buildPropertiesFile()*.

These properties are:
- build.date
- build.version
- copyright

To have any of these variables constructed by concatenating other properties/text together, declare a property named
<property.name>*.format* in one of the above mentioned property files

example:
```properties
build.date.day=13
build.date.month=February
build.date.year=2021

#Note: Expression must be a valid slel expression.
#below ends up being injected into build.date
build.date.format=${build.date.day} ${build.date.month} ${build.date.year}
```

## Declaring dependencies and licenses
While dependencies (and transitive dependencies) for any used Legyver libraries will be automatically added to the About Page, the developer can also declare additional open source libraries.
- Manually with a properties file
- Automatically by implementing the com.legyver.core.license.LicenseService

### Adding licenses via a properties file
Construct a property file below and pass it to the AboutPageOptions.Builder
```java
AboutPageOptions aboutPageOptions = new AboutPageOptions.Builder(getClass())
        .dependenciesFile("license.properties")
		
```
In a property file, for any opensource library used by your application:
1. Create a property with of the library name with its license
1. Create a property with the same prefix appended by **.link.0** which has the link to the license

example:
```properties
commons-lang3=Apache License 2.0
commons-lang3.link.0=https://github.com/apache/commons-lang/blob/master/LICENSE.txt
com.legyver.utils=Apache License 2.0
com.legyver.utils.link.0=https://github.com/Legyver/utils/blob/master/LICENSE
```

If a license has more than one license associated with it
1. Add the library name as before, but use a **'/'** character between the license names
1. Specify the link to the first as above with the 0 suffix
1. Specify the link to the second as above with the suffix number incremented by 1.

example:
```properties
io.icomoon=GPL/CC BY 4.0
io.icomoon.link.0=http://www.gnu.org/licenses/gpl.html
io.icomoon.link.1=http://creativecommons.org/licenses/by/4.0/
```
### Implementing the LicenseService
The license service is a transitive dependency for the fenxlib library so there is no additional import.

```java
public class LicenseServiceImpl implements LicenseService {

	@Override
	public Properties loadLicenseProperties() throws IOException {
		Properties properties = new Properties();
		try (InputStream inputStream = LicenseServiceImpl.class.getResourceAsStream("license.properties")) {
			properties.load(inputStream);
		}
		return properties;
	}
}
```
The above example will read the **license.properties** file from the same package as the LicenseServiceImpl.

**Note:** for Java 9+, you want your license service to load the properties itself due to visibility constraints.  By default, the ClassLoader only has access to open packages.

For modular applications, providing your library is modular too, you can declare this service in the module-info.java
```
provides com.legyver.core.license.LicenseService with your.package.LicenseServiceImpl;
```

If either are not modular, you will need to specify your license service in **META-INF/services/com.legyver.core.license.LicenseService**
```
your.package.LicenseServiceImpl
```

Obviously using a LicenseService does not eliminate the need for a property file somewhere, but it does help manage licenses of any libraries you may be writing yourself, as any dependency changes in those libraries will automatically be picked up by the AboutPage in any downstream application.