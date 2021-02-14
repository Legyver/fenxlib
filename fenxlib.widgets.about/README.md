# AboutPage
Display an about page with the following customizable elements (listed from top-to-bottom)
- A title for the about page
- A build message specified by a **build.message** property.  See property transformation rules below.
- An introduction paragraph
- Two additional paragraphs
- A hard-coded message ***Powered by open source***
- A list of all open source dependencies, and their licenses read from a union of
  - licensePropertiesFile() specified by the developer
  - Any dependency license information read from Legyver's self-reporting modules.
- A copyright message specified by a **copyright** message
## Property Transformation Rules
Three properties are required to be constructed by a property graph comprised of the properties defined in the copyrightPropertiesFile() and buildPropertiesFile().

These properties are:
- build.date
- build.version
- copyright

To have any of these variables constructed by concatenating other properties/text together, declare a property named
<property.name>.format

example:
```properties
build.date.day=13
build.date.month=February
build.date.year=2021

#Note: Expression must be a valid slel expression.
#below ends up being injected into build.date
build.date.format=${build.date.day} ${build.date.month} ${build.date.year}
```

## Usage
### Dependency
```gradle
    implementation group: 'com.legyver', name: 'fenxlib.widgets.about', version: '2.0.0.0-alpha-5'
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