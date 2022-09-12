# Releases
## 3.0.0
Since this introduces MaterialFX as an alternative to JFoenix (dropped from support as it does not appear to be actively maintained), and the non-trivial ways these frameworks handle fonts, this is going to be a milestone release versus a breaking release, to give merit to the resourceScope of the changes. Not the least of this will be how factories are handled.

Instead of calling a factory explicitly, you will call the general factory which will spit out the output of the first related implementation on the classpath, be it raw JavaFX or MaterialFX.  For example if the materialfx factory is on the classpath, when calling for a text box you will get a MaterialFX text box.

If nothing is on the classpath, you will get the plain JavaFX TextBox.  This will be extendable by allowing you to bring your own factory.  If your factory has a higher preference than others (JavaFX is 0, MaterialFX is 100; the lower the number, the higher the preference) it will usurp other factories for that specific control.  You can also define a factory for custom types that has a lower preference and your factory will still be picked up.

Even though we plan on supporting MaterialFX controls, we will not be using their alert system as it does not work on multi-monitor displays (alerts are always displayed over primary monitor).

### Versioning
From 3.0.0-alpha.1 we are switching to [Semantic Versioning](https://semver.org/).

### Refactoring
- fenxlib.api
  - Renamed from fenxlib.core.api
  - Contains just enough to bootstrap the fenxlib.test
- fenxlib.core
  - Renamed from fenxlib.core.impl 
  - Pulled icon handling into core
    - Devs are expected to either use the fenxlib.icons.standard font or supply their own ttf
  - Rather than building a UI out of nested factories (as was the case in 1.0-2.X), use a static factory to lookup to instantiate the control via the appropriate service.
    - This also means that developers will be expected to extend Control and SkinBase to build their custom UIs.
- fenxlib.controls.materialfx
  - Host the new MaterialFX (preferred) factories
  - package: com.legyver.fenxlib.controls.materialfx
- fenxlib.icons.fa
  - Deleted.  We no longer follow the SVG pattern from JFoenix, but rather rely on specific TTFs the dev can supply.
- fenxlib.icons.standard
  - IcoMoon-Free icons
  - This is a separate module because of licensing.  Licensing should only include IcoMoon if you're using them.
- fenxlib.widgets.about
  - Support for v2 license property format for more complete license information 
### Testing
- IntegrationTests are now moved out of the individual modules and into their own top-level directory ([integration_tests](integration_tests/README.md)).
- Unit tests that do not require JavaFX or the Fenxlib framework, can remain in the test directory of the relevant project.
- We also provide a [fenxlib.test](fenxlib.test/README.md) base test case built on TestFx

## 2.1.2.6
### Maintenance
- All
  - Removed requires transitive for JavaFX modules
- Core
  - ApplicationOptions should allow the application name to be retrieved since it was specified in the builder 
- TukTukFX
  - TaskExecutor should clear ThreadContext before returning thread to pool.
- Widgets-about
  - Now exports com.legyver.fenxlib.widgets.license
## 2.1.2.5
### Vulnerability
- Update log4j version to 2.17.1
## 2.1.2.4
### Vulnerability
- Update log4j version to 2.17.0
## 2.1.2.3
### Refactoring
- ConfigService accepts an initializer so FileIOUtil can always use the same ApplicationConfigInstantiator
### Vulnerability
- Update log4j version to 2.15.0
## 2.1.2.2
### Refactoring
- DesktopWeblink
  - Added constructor to accept a URI.
## 2.1.2.1
### Refactoring
- FileTreeExplorer
  - Pulled out DefaultFileTreeItemContextMenuFactory for easier extension.
  - Deprecated the FileWatchHandler.Builder.contextMenuItemFactory(FileTreeItemContextMenuItemFactory) method as it is confusing to use.
 - BottomRegionFactory now allows for center content factories to be passed into constructor.
## 2.1.2
### New Stuff
- DesktopWeblink
  - Open up a link in the user's default web browser
## 2.1.1
### New Stuff
- Add notification support for info/warning/error messages
- FileTreeExplorer
  - Add ability to provide short-cut menu items to FileTreeExplorer (FileWatchHandler.Builder().contextMenuItemFactory)
## 2.1
### New Stuff
- New widget FileTreeExplorer
  - Standard filesystem project window
  - Add top level folders via import window
  - All folders are automatically watched so that folder contents automatically updates as files/folders are added or deleted.
    - Files/Folders added on filesystem under a watched directory automatically added to the tree
    - Files/Folders removed on filesystem under a watched directory automatically removed from the tree
  - All nodes in tree automatically sorted in alphabetical order
    - Folders above files
- Support for correlation ids in log messages
### Refactoring
- Standardized module names
  - fenxlib.icons.fa -> com.legyver.fenxlib.icons.fa
  - fenxlib.icons.icomoon -> com.legyver.fenxlib.icons.icomoon
- Moved classes from core.impl to core.api
  - com.legyver.fenxlib.core.impl.util.GuidUtil to com.legyver.fenxlib.core.util.GuidUtil
- Added SHUTDOWN lifecycle hook to follow PRE_SHUTDOWN lifecycle hooks.
  - File > Exit now triggers SHUTDOWN lifecycle hook, as does closing the window
  - fenxlib.extensions.tuktukfx now shuts down the thread pool on SHUTDOWN lifecycle hook
## 2.0
Milestone release.  All fenxlib libraries now live in one repository as separate modules under various top-level folders
### New stuff
- Application Lifecycle
  - Lifecycle hook registration Mixins
  - AppStateObserver
- AboutPage (see fenxlib-samples repository for a demo)
  - Widget to simplify about page creation
    - Two customizable about paragraphs
    - "Powered by open source" license section detailing the licenses of the libraries used provided by LicenseService.
    - Templateable (SLEL) build message
    - Templateable (SLEL) copyright message
- ConfigService
  - Loads the configuration from file/resources via the LoadConfigApplicationLifecycleHook
  - Updates the saved configuration pre-shutdown via the AutoSaveConfigApplicationLifecycleHook unless disableAutosaveConfig() is specified
  - Overrideable by supplying an additional ConfigService with a lower order() via standard java/services mechanism 
- IconService
  - Loads any available font libraries when the SVGGlyphLoadingApplicationLifecycleHook is used
    - available font library modules:
      - fenxlib.icons.fa - free regular and solid FontAwesome icons
      - fenxlib.icons.icomoon - free IcoMoon icons
  - Supports loading additional fonts by supplying an additional IconService via standard java/services mechanism
- LicenseService
  - Support for libraries supplying their own license.properties.  When using the AboutPage widget, the dependencies will automatically include licenses of dependencies
  - Supports loading additional licenses by supplying an additional LicenseService via standard java/services mechanism
### Refactoring
- ApplicationOptions
  - Extendable Builder replacing tall ApplicationOptions inheritance stack.
  - Mixins for adding ApplicationLifecycleHooks
- Package renaming.
  - Most functionality of fenxlib-1.0 now lives in the core package (com.legyver.fenxlib.core).
     but has been split between api and impl.
  - The number skins now lives in the fenxlib.skins.number module
  - The blade widget now lives in the fenxlib.widgets.blade module
- Module
  - Made the whole library Java module friendly
    - fenxlib.core.api
    - fenxlib.core.impl
    - fenxlib.controls.svg
    - fenxlib.extensions.tuktukfx
    - fenxlib.icons.fa
    - fenxlib.icons.icomoon
    - fenxlib.skins.number
    - fenxlib.widgets.about
    - fenxlib.widgets.blade
- Application configuration files
    - Changed to use Jackson instead of gson
    - Dates/Times now comply to ISO-8601 by default
## 1.1.1
### New stuff
- Factories
  - components
    - JFXTabPaneFactory
    - TabContentFactory
    - TabPaneFactory
### Refactoring
- Changes to support Tabs
  - NodeFactory now extends StyleableFactory
  - AbstractWrappingFactory parameterized type changed from Node to Styleable

## 1.1
### New stuff
- Factories
  - components
    - TextFactory
    - TextFlowFactory
  - regions
    - PopupMenuItemFactory
- Decorators
  - styles
    - StyleDecorator
- Utilities
  - Support for storing application config files on Windows in APPDATA
  - Support for multiple pre_shutdown hooks 
### Refactoring
  - Moved file-centric utilities to 'files' package
  - Moved ApplicationOptions-related stuff to 'options' package

## 1.0
### New stuff
- Factories
  - components
    - AccordionMenuFactory
    - BladeFactory
    - BorderPaneFactory
    - DatePickerFactory
    - HBoxFactory
    - JFXDialogLayoutFactory
    - JFXListViewFactory
    - LabelFactory
    - ListViewFactory
    - MoreFieldFactory
    - RegionFactory
    - SliderFactory
    - SplitPaneFactory
    - StackPaneFactory
    - SvgIconFactory
    - SystemBrowserHyperlinkFactory
    - TextFieldFactory
    - TitledPaneFactory
    - VBoxFactory
    - WebViewFactory
  - regions
    - BottomRegionFactory
    - TopRegionFactory
  - menus
    - CloseCurrentFileFactory
    - FileOptionsChooserFactory
    - OpenRecentFileFactory
    - RecentlyOpenedFileFactory
    - SaveCurrentFileFactory
    - SelectDirectoryMenuFactory
    - SeparatorMenuItemFactory
    - ZipFileMenuFactory
- Decorators
  - styles
    - ButtonIconDecorator
    - ButtonTooltipDecorator
    - CssClassDecorator
  - menus
    - OpenFileDecorator
    - SaveFileDecorator
  - utils
    - GuidNodeDecorator
    - RegisterAsDecorator
- Skins
  - CurrencyFieldSkin
  - PercentageFieldSkin
- Utilities
  - AbstractBindingFactory
  - QueryableComponentRegistry
  - NamedComponentQuery
  - TypedComponentQuery
  - Application Lifecycle hooks
  - Support for storing application config files in user.home