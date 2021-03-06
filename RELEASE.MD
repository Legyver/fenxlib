# Releases
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